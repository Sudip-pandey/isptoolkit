package com.pandey.isptoolkit.core.util

import com.pandey.isptoolkit.domain.model.SubnetResult
import java.net.InetAddress
import kotlin.math.pow

object SubnetCalculatorUtil {

    fun calculateIPv4(ipStr: String, cidr: Int): SubnetResult {
        require(cidr in 0..32) { "CIDR prefix must be between 0 and 32" }
        
        val ipInt = ipTo32BitInt(ipStr)
        val maskInt = if (cidr == 0) 0 else (0xFFFFFFFF.toLong() shl (32 - cidr)).toInt()
        val networkInt = ipInt and maskInt
        val wildcardInt = maskInt.inv()
        val broadcastInt = networkInt or wildcardInt

        val totalHosts = (2.0.pow((32 - cidr).toDouble()) - 2).toLong().coerceAtLeast(0)
        
        val firstUsableInt = if (cidr >= 31) networkInt else networkInt + 1
        val lastUsableInt = if (cidr >= 31) broadcastInt else broadcastInt - 1

        return SubnetResult(
            ipAddress = ipStr,
            cidr = cidr,
            networkAddress = intToIp(networkInt),
            broadcastAddress = intToIp(broadcastInt),
            subnetMask = intToIp(maskInt),
            wildcardMask = intToIp(wildcardInt),
            firstUsableIp = intToIp(firstUsableInt),
            lastUsableIp = intToIp(lastUsableInt),
            usableHostCount = totalHosts
        )
    }

    private fun ipTo32BitInt(ipStr: String): Int {
        val bytes = InetAddress.getByName(ipStr).address
        var result = 0
        for (b in bytes) {
            result = (result shl 8) or (b.toInt() and 0xFF)
        }
        return result
    }

    private fun intToIp(i: Int): String {
        return "${(i shr 24) and 0xFF}.${(i shr 16) and 0xFF}.${(i shr 8) and 0xFF}.${i and 0xFF}"
    }
}