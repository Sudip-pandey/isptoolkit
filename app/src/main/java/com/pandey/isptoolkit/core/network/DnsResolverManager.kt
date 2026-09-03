package com.pandey.isptoolkit.core.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetAddress
import javax.inject.Inject
import javax.inject.Singleton

data class DnsRecordResult(
    val queryHost: String,
    val recordType: String,
    val resolvedAddresses: List<String>,
    val durationMs: Long,
    val isSuccess: Boolean,
    val errorMessage: String? = null
)

@Singleton
class DnsResolverManager @Inject constructor() {
    suspend fun resolveA(hostname: String): DnsRecordResult = withContext(Dispatchers.IO) {
        val start = System.currentTimeMillis()
        try {
            val addresses = InetAddress.getAllByName(hostname).map { it.hostAddress }
            DnsRecordResult(
                queryHost = hostname,
                recordType = "A / AAAA",
                resolvedAddresses = addresses,
                durationMs = System.currentTimeMillis() - start,
                isSuccess = true
            )
        } catch (e: Exception) {
            DnsRecordResult(
                queryHost = hostname,
                recordType = "A / AAAA",
                resolvedAddresses = emptyList(),
                durationMs = System.currentTimeMillis() - start,
                isSuccess = false,
                errorMessage = e.message ?: "DNS Resolution Failed"
            )
        }
    }
}