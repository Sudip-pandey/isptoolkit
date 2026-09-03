package com.pandey.isptoolkit.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "devices")
data class DeviceEntity(
    @PrimaryKey val ipAddress: String,
    val ipv6Address: String? = null,
    val hostname: String? = null,
    val macAddress: String = "Not provided by Android",
    val vendor: String = "Unknown",
    val deviceType: String = "UNKNOWN",
    val confidence: String = "LOW",
    val isOnline: Boolean = true,
    val firstSeen: Long = System.currentTimeMillis(),
    val lastSeen: Long = System.currentTimeMillis(),
    val isSaved: Boolean = false,
    val userNote: String = ""
)