package com.pandey.isptoolkit.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.pandey.isptoolkit.core.model.NetworkState
import dagger.hilt.android.qualifiers.ApplicationContext
import java.net.Inet4Address
import java.net.NetworkInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkInfoProvider @Inject constructor(
    @ApplicationContext private val context: Context,
    private val wifiInfoProvider: WifiInfoProvider
) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun getActiveNetworkState(): NetworkState {
        val activeNetwork = connectivityManager.activeNetwork ?: return NetworkState()
        val caps = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return NetworkState()
        val linkProperties = connectivityManager.getLinkProperties(activeNetwork)

        val isConnected = caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        val isVpn = caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
        
        var connType = "Unknown"
        if (caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) connType = "Wi-Fi"
        else if (caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) connType = "Cellular"
        else if (caps.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) connType = "Ethernet"

        var ipv4 = "Unavailable"
        var ipv6 = "Unavailable"

        try {
            val interfaces = NetworkInterface.getNetworkInterfaces()
            while (interfaces.hasMoreElements()) {
                val iface = interfaces.nextElement()
                if (iface.isLoopback || !iface.isUp) continue
                val addrs = iface.inetAddresses
                while (addrs.hasMoreElements()) {
                    val addr = addrs.nextElement()
                    if (!addr.isLoopbackAddress) {
                        if (addr is Inet4Address && ipv4 == "Unavailable") {
                            ipv4 = addr.hostAddress ?: "Unavailable"
                        } else if (ipv6 == "Unavailable" && !addr.isLoopbackAddress) {
                            ipv6 = addr.hostAddress?.substringBefore("%") ?: "Unavailable"
                        }
                    }
                }
            }
        } catch (_: Exception) {}

        val gateway = linkProperties?.routes?.firstOrNull { it.isDefaultRoute }?.gateway?.hostAddress ?: "Unavailable"
        val dnsServers = linkProperties?.dnsServers?.mapNotNull { it.hostAddress } ?: emptyList()

        val wifiState = if (connType == "Wi-Fi") wifiInfoProvider.getCurrentWifiDetails() else NetworkState()

        return wifiState.copy(
            isConnected = isConnected,
            connectionType = connType,
            ipv4Address = ipv4,
            ipv6Address = ipv6,
            gatewayIp = gateway,
            dnsServers = dnsServers,
            isVpnActive = isVpn,
            isInternetValidated = caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        )
    }
}