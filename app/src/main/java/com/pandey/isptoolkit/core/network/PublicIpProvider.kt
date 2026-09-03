package com.pandey.isptoolkit.core.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PublicIpProvider @Inject constructor() {
    private val client = OkHttpClient.Builder().build()

    suspend fun fetchPublicIp(): String = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url("https://api.ipify.org")
                .build()
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    response.body?.string()?.trim() ?: "Unavailable"
                } else "Unavailable"
            }
        } catch (_: Exception) {
            "Unavailable"
        }
    }
}