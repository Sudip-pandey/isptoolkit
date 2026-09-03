package com.pandey.isptoolkit.core.util

data class IPv6Analysis(
    val fullAddress: String,
    val prefixLength: Int,
    val networkPrefix: String,
    val classification: String,
    val isMulticast: Boolean,
    val isLinkLocal: Boolean,
    val isLoopback: Boolean
)

object IPv6CalculatorUtil {
    fun analyze(addressStr: String, prefixLength: Int = 64): IPv6Analysis {
        val addr = addressStr.trim()
        val isLink = addr.lowercase().startsWith("fe80")
        val isLoop = addr == "::1" || addr == "0:0:0:0:0:0:0:1"
        val isMulti = addr.lowercase().startsWith("ff")

        val classification = when {
            isLoop -> "Loopback"
            isLink -> "Link-Local Unicast"
            isMulti -> "Multicast"
            addr.lowercase().startsWith("2") || addr.lowercase().startsWith("3") -> "Global Unicast"
            addr.lowercase().startsWith("fd") || addr.lowercase().startsWith("fc") -> "Unique Local (ULA)"
            else -> "Reserved / Special Purpose"
        }

        val prefix = if (addr.contains("::")) {
            "${addr.substringBefore("::")}::/$prefixLength"
        } else {
            "${addr.take(19)}::/$prefixLength"
        }

        return IPv6Analysis(
            fullAddress = addr,
            prefixLength = prefixLength,
            networkPrefix = prefix,
            classification = classification,
            isMulticast = isMulti,
            isLinkLocal = isLink,
            isLoopback = isLoop
        )
    }
}