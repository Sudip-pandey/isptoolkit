package com.pandey.isptoolkit.data.repository

import com.pandey.isptoolkit.core.model.NetworkState
import javax.inject.Inject

class NetworkRepository @Inject constructor() {
    fun getCurrentNetworkState(): NetworkState {
        return NetworkState(
            isConnected = true,
            connectionType = "Wi-Fi",
            ssid = "ISP-Guest",
            rssi = -58,
            ipv4Address = "192.168.1.15",
            gatewayIp = "192.168.1.1",
            isInternetValidated = true
        )
    }
}
