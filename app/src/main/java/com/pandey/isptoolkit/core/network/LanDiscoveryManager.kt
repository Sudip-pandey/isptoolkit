package com.pandey.isptoolkit.core.network

import com.pandey.isptoolkit.core.model.DeviceInfo
import com.pandey.isptoolkit.core.util.DeviceClassifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.InetAddress
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanDiscoveryManager @Inject constructor() {

    fun discoverLanDevices(subnetPrefix: String, gatewayIp: String): Flow<List<DeviceInfo>> = flow {
        val discoveredList = mutableListOf<DeviceInfo>()
        emit(discoveredList.toList())

        // Bounded concurrency over local subnet range .1 to .254
        coroutineScope {
            val tasks = (1..254).map { host ->
                async(Dispatchers.IO) {
                    val targetIp = "$subnetPrefix.$host"
                    try {
                        val inet = InetAddress.getByName(targetIp)
                        val start = System.currentTimeMillis()
                        if (inet.isReachable(300)) {
                            val latency = System.currentTimeMillis() - start
                            val hostname = inet.canonicalHostName.takeIf { it != targetIp }
                            
                            val (type, confidence) = DeviceClassifier.classify(
                                hostname = hostname,
                                services = emptyList(),
                                ipAddress = targetIp,
                                gatewayIp = gatewayIp
                            )

                            DeviceInfo(
                                ipAddress = targetIp,
                                hostname = hostname,
                                latencyMs = latency,
                                isOnline = true,
                                deviceType = type,
                                confidence = confidence,
                                macAddress = "Not provided by Android",
                                vendor = "Unknown"
                            )
                        } else null
                    } catch (_: Exception) {
                        null
                    }
                }
            }

            val results = tasks.awaitAll().filterNotNull()
            discoveredList.addAll(results)
            emit(discoveredList.toList())
        }
    }.flowOn(Dispatchers.IO)
}