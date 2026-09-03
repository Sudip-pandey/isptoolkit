package com.pandey.isptoolkit.core.model

data class WifiScanResultInfo(
    val ssid: String,
    val bssid: String,
    val rssi: Int,
    val channel: Int,
    val frequency: Int,
    val band: String,
    val capabilities: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isConnected: Boolean = false
)