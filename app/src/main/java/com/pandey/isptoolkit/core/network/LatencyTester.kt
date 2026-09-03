package com.pandey.isptoolkit.core.network

import com.pandey.isptoolkit.core.model.PingSample
import com.pandey.isptoolkit.core.model.PingSummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetSocketAddress
import java.net.Socket
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LatencyTester @Inject constructor() {

    suspend fun executePing(targetHost: String, count: Int = 4, timeoutMs: Int = 2000): PingSummary =
        withContext(Dispatchers.IO) {
            val samples = mutableListOf<PingSample>()
            var min = Long.MAX_VALUE
            var max = 0L
            var total = 0L
            var successCount = 0

            for (i in 1..count) {
                val start = System.currentTimeMillis()
                var success = false
                var error: String? = null

                try {
                    val socket = Socket()
                    socket.connect(InetSocketAddress(targetHost, 80), timeoutMs)
                    socket.close()
                    val duration = System.currentTimeMillis() - start
                    success = true
                    successCount++
                    min = minOf(min, duration)
                    max = maxOf(max, duration)
                    total += duration
                    samples.add(PingSample(i, duration, true))
                } catch (e: Exception) {
                    error = e.message ?: "Connection Timeout"
                    samples.add(PingSample(i, -1, false, error))
                }
            }

            val loss = ((count - successCount).toFloat() / count) * 100f
            val avg = if (successCount > 0) total / successCount else -1L

            PingSummary(
                targetHost = targetHost,
                totalSent = count,
                totalReceived = successCount,
                packetLossPercent = loss,
                minLatencyMs = if (min == Long.MAX_VALUE) -1 else min,
                maxLatencyMs = max,
                avgLatencyMs = avg,
                samples = samples
            )
        }
}