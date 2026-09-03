package com.pandey.isptoolkit.core.model

data class NetworkState(
    val isConnected: Boolean = false,
    val connectionType: String = "Disconnected",
    val ssid: String = "Unavailable",
    val bssid: String = "Unavailable",
    val ipv4Address: String = "Unavailable",
    val ipv6Address: String = "Unavailable",
    val gatewayIp: String = "Unavailable",
    val dnsServers: List<String> = emptyList(),
    val wifiBand: String = "Unavailable",
    val channel: Int = 0,
    val frequency: Int = 0,
    val rssi: Int = -127,
    val signalRating: String = "No Signal",
    val linkSpeedMbps: Int = 0,
    val publicIp: String = "Checking...",
    val isVpnActive: Boolean = false,
    val isInternetValidated: Boolean = false
)