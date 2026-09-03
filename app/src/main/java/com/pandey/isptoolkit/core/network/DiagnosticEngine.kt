package com.pandey.isptoolkit.core.network

import com.pandey.isptoolkit.core.model.DiagnosticTestResult
import com.pandey.isptoolkit.core.model.FullDiagnosticReport
import com.pandey.isptoolkit.core.model.TestStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DiagnosticEngine @Inject constructor(
    private val networkInfoProvider: NetworkInfoProvider,
    private val latencyTester: LatencyTester,
    private val dnsResolverManager: DnsResolverManager
) {
    suspend fun runFullDiagnostics(): FullDiagnosticReport {
        val results = mutableListOf<DiagnosticTestResult>()
        val state = networkInfoProvider.getActiveNetworkState()

        // 1. Connection Test
        if (state.isConnected) {
            results.add(DiagnosticTestResult("Local Connectivity", TestStatus.PASS, state.connectionType, "Active Interface: ${state.ipv4Address}", "Device is connected to an active network interface."))
        } else {
            results.add(DiagnosticTestResult("Local Connectivity", TestStatus.FAIL, "Disconnected", "No active default network", "Device is completely offline."))
        }

        // 2. Wi-Fi RSSI Test
        if (state.connectionType == "Wi-Fi") {
            val status = when {
                state.rssi >= -65 -> TestStatus.PASS
                state.rssi >= -78 -> TestStatus.WARNING
                else -> TestStatus.FAIL
            }
            results.add(DiagnosticTestResult("Wi-Fi Signal Strength", status, "${state.rssi} dBm", "SSID: ${state.ssid}, Band: ${state.wifiBand}", "Signal level affects local PHY rate stability."))
        }

        // 3. Gateway Ping Test
        if (state.gatewayIp != "Unavailable") {
            val ping = latencyTester.executePing(state.gatewayIp, count = 3)
            val status = if (ping.packetLossPercent == 0f && ping.avgLatencyMs < 20) TestStatus.PASS else if (ping.packetLossPercent < 20f) TestStatus.WARNING else TestStatus.FAIL
            results.add(DiagnosticTestResult("Gateway Reachability", status, "${ping.avgLatencyMs} ms", "Gateway: ${state.gatewayIp}, Loss: ${ping.packetLossPercent}%", "Local router response metrics."))
        }

        // 4. DNS Resolution Test
        val dnsTest = dnsResolverManager.resolveA("google.com")
        if (dnsTest.isSuccess) {
            results.add(DiagnosticTestResult("DNS Resolution", TestStatus.PASS, "${dnsTest.durationMs} ms", "Resolved google.com -> ${dnsTest.resolvedAddresses.firstOrNull()}", "DNS queries are completing normally."))
        } else {
            results.add(DiagnosticTestResult("DNS Resolution", TestStatus.FAIL, "Failed", dnsTest.errorMessage ?: "Timeout", "DNS lookup failure prevents domain routing."))
        }

        val passCount = results.count { it.status == TestStatus.PASS }
        val score = ((passCount.toFloat() / results.size) * 100).toInt()

        return FullDiagnosticReport(
            healthScore = score,
            overallSummary = if (score > 80) "Network operates within healthy parameters." else "Issues detected requiring field investigation.",
            results = results
        )
    }
}