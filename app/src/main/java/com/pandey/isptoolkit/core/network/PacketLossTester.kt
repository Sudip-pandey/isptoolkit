package com.pandey.isptoolkit.core.network

import com.pandey.isptoolkit.core.model.PingSummary
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PacketLossTester @Inject constructor(
    private val latencyTester: LatencyTester
) {
    suspend fun testPacketLoss(targetHost: String, totalAttempts: Int = 20): PingSummary {
        return latencyTester.executePing(targetHost, count = totalAttempts, timeoutMs = 1000)
    }
}