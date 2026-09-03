package com.pandey.isptoolkit.data.repository

import com.pandey.isptoolkit.core.model.NetworkState
import javax.inject.Inject

class NetworkRepository @Inject constructor() {
    fun getCurrentNetworkState(): NetworkState {
        return NetworkState(
            isConnected = true,
            ssid = "ISP-Guest",
            signalStrength = -58,
            ipAddress = "192.168.1.15",
            gateway = "192.168.1.1"
        )
    }
}
