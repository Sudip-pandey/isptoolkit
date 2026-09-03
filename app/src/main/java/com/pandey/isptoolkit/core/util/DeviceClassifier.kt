package com.pandey.isptoolkit.core.util

import com.pandey.isptoolkit.core.model.ConfidenceLevel
import com.pandey.isptoolkit.core.model.DeviceType

object DeviceClassifier {
    fun classify(
        hostname: String?,
        services: List<String>,
        ipAddress: String,
        gatewayIp: String
    ): Pair<DeviceType, ConfidenceLevel> {
        if (ipAddress == gatewayIp) {
            return Pair(DeviceType.ROUTER, ConfidenceLevel.HIGH)
        }

        val name = hostname?.lowercase() ?: ""
        val srv = services.map { it.lowercase() }

        return when {
            name.contains("ont") || name.contains("onu") || name.contains("gpon") -> 
                Pair(DeviceType.ONT, ConfidenceLevel.HIGH)
            name.contains("tv") || name.contains("chromecast") || name.contains("roku") -> 
                Pair(DeviceType.SMART_TV, ConfidenceLevel.HIGH)
            name.contains("printer") || srv.any { it.contains("ipp") || it.contains("printer") } -> 
                Pair(DeviceType.PRINTER, ConfidenceLevel.HIGH)
            name.contains("cam") || srv.any { it.contains("rtsp") || it.contains("onvif") } -> 
                Pair(DeviceType.CAMERA, ConfidenceLevel.MEDIUM)
            name.contains("nvr") || name.contains("dvr") -> 
                Pair(DeviceType.NVR, ConfidenceLevel.MEDIUM)
            name.contains("nas") || name.contains("synology") || name.contains("qnap") -> 
                Pair(DeviceType.NAS, ConfidenceLevel.HIGH)
            name.contains("android") || name.contains("iphone") || name.contains("galaxy") -> 
                Pair(DeviceType.PHONE, ConfidenceLevel.MEDIUM)
            name.contains("macbook") || name.contains("laptop") -> 
                Pair(DeviceType.LAPTOP, ConfidenceLevel.MEDIUM)
            srv.any { it.contains("http") || it.contains("ssh") } -> 
                Pair(DeviceType.ACCESS_POINT, ConfidenceLevel.LOW)
            else -> 
                Pair(DeviceType.UNKNOWN, ConfidenceLevel.LOW)
        }
    }
}