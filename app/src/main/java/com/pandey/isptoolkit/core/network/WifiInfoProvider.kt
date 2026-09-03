package com.pandey.isptoolkit.core.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import com.pandey.isptoolkit.core.model.NetworkState
import com.pandey.isptoolkit.core.model.WifiScanResultInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WifiInfoProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val wifiManager =
        context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    @SuppressLint("MissingPermission")
    fun getCurrentWifiDetails(): NetworkState {
        val info = wifiManager.connectionInfo ?: return NetworkState()

        val rssi = info.rssi
        val freq = info.frequency
        val ssid = if (info.ssid != null && info.ssid != "<unknown ssid>") info.ssid.replace("\"", "") else "Permission required"
        val bssid = info.bssid ?: "Unavailable"

        val band = when {
            freq >= 5925 -> "6 GHz"
            freq >= 4900 -> "5 GHz"
            freq >= 2400 -> "2.4 GHz"
            else -> "Unknown"
        }

        val channel = getChannelFromFrequency(freq)
        val rating = when {
            rssi >= -50 -> "Excellent"
            rssi >= -65 -> "Good"
            rssi >= -75 -> "Fair"
            rssi >= -85 -> "Poor"
            else -> "No Signal"
        }

        return NetworkState(
            ssid = ssid,
            bssid = bssid,
            wifiBand = band,
            channel = channel,
            frequency = freq,
            rssi = rssi,
            signalRating = rating,
            linkSpeedMbps = info.linkSpeed
        )
    }

    @SuppressLint("MissingPermission")
    fun getScanResults(): List<WifiScanResultInfo> {
        return try {
            wifiManager.scanResults.map { scan ->
                val band = when {
                    scan.frequency >= 5925 -> "6 GHz"
                    scan.frequency >= 4900 -> "5 GHz"
                    scan.frequency >= 2400 -> "2.4 GHz"
                    else -> "Unknown"
                }
                WifiScanResultInfo(
                    ssid = scan.SSID.ifEmpty { "[Hidden Network]" },
                    bssid = scan.BSSID ?: "Permission required",
                    rssi = scan.level,
                    channel = getChannelFromFrequency(scan.frequency),
                    frequency = scan.frequency,
                    band = band,
                    capabilities = scan.capabilities ?: "Unknown",
                    timestamp = System.currentTimeMillis()
                )
            }
        } catch (_: Exception) {
            emptyList()
        }
    }

    private fun getChannelFromFrequency(freq: Int): Int {
        return when {
            freq in 2412..2484 -> (freq - 2412) / 5 + 1
            freq in 5170..5825 -> (freq - 5170) / 5 + 34
            freq in 5955..7115 -> (freq - 5955) / 5 + 1
            else -> 0
        }
    }
}