package com.pandey.isptoolkit.domain.usecase

import com.pandey.isptoolkit.core.network.LatencyTester
import com.pandey.isptoolkit.core.network.NetworkInfoProvider
import com.pandey.isptoolkit.domain.model.ComplaintDiagnosis
import com.pandey.isptoolkit.domain.model.ComplaintType
import javax.inject.Inject

class RunCustomerWorkflowUseCase @Inject constructor(
    private val networkInfoProvider: NetworkInfoProvider,
    private val latencyTester: LatencyTester
) {
    suspend fun diagnose(type: ComplaintType): ComplaintDiagnosis {
        val state = networkInfoProvider.getActiveNetworkState()

        return when (type) {
            ComplaintType.NO_INTERNET -> {
                if (!state.isConnected) {
                    ComplaintDiagnosis("Wi-Fi Disconnected", "Local device is not attached to any access point.", "Check Wi-Fi connection and SSID credentials.")
                } else if (state.gatewayIp != "Unavailable") {
                    val ping = latencyTester.executePing(state.gatewayIp, count = 2)
                    if (ping.packetLossPercent == 100f) {
                        ComplaintDiagnosis("LAN Gateway Unreachable", "Default Gateway (${state.gatewayIp}) drops 100% packets.", "Inspect local Ethernet cable / router power.")
                    } else {
                        ComplaintDiagnosis("WAN / Upstream Outage", "Gateway reachable (${ping.avgLatencyMs}ms) but public resolution fails.", "Inspect ONT optical power & WAN status.")
                    }
                } else {
                    ComplaintDiagnosis("DHCP / Gateway Assignment Failure", "IP acquired but Gateway is missing.", "Verify router DHCP server lease status.")
                }
            }
            ComplaintType.WEAK_WIFI -> {
                ComplaintDiagnosis(
                    likelyIssue = if (state.rssi < -75) "Poor Wi-Fi Coverage" else "Interference / Congestion",
                    evidence = "Current RSSI: ${state.rssi} dBm on channel ${state.channel} (${state.wifiBand})",
                    recommendedAction = "Reposition router, switch Wi-Fi band to 5GHz, or add an Access Point."
                )
            }
            ComplaintType.INTERNET_SLOW -> {
                ComplaintDiagnosis("Congested Bandwidth or High Latency", "Signal: ${state.rssi} dBm, Link speed: ${state.linkSpeedMbps} Mbps.", "Run Speed Test & verify background local network traffic.")
            }
            ComplaintType.HIGH_PING -> {
                ComplaintDiagnosis("Local Wireless Latency Jitter", "Gateway Latency check required.", "Test via Ethernet to isolate Wi-Fi bufferbloat from ISP WAN routing.")
            }
            ComplaintType.ONT_LOS -> {
                ComplaintDiagnosis("Fiber Cut or Optical Loss", "User reported Red LOS on ONU.", "Check fiber patch cord, clean SC/APC connector, check OLT RX power.")
            }
        }
    }
}