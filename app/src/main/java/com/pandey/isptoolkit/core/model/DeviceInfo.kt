package com.pandey.isptoolkit.core.model

enum class DeviceType {
    ROUTER, ONT, PHONE, LAPTOP, DESKTOP, SMART_TV,
    PRINTER, CAMERA, NVR, ACCESS_POINT, NAS, IOT, UNKNOWN
}

enum class ConfidenceLevel { HIGH, MEDIUM, LOW }

data class DeviceInfo(
    val ipAddress: String,
    val ipv6Address: String? = null,
    val hostname: String? = null,
    val macAddress: String = "Not provided by Android",
    val vendor: String = "Unknown",
    val deviceType: DeviceType = DeviceType.UNKNOWN,
    val confidence: ConfidenceLevel = ConfidenceLevel.LOW,
    val latencyMs: Long = -1,
    val isOnline: Boolean = true,
    val firstSeen: Long = System.currentTimeMillis(),
    val lastSeen: Long = System.currentTimeMillis(),
    val advertisedServices: List<String> = emptyList(),
    val discoveryEvidence: String = "Direct Observation"
)