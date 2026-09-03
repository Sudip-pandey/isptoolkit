package com.pandey.isptoolkit.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sites")
data class SiteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val siteName: String,
    val customerReference: String = "",
    val routerModel: String = "Unknown",
    val ontModel: String = "Unknown",
    val internetPlan: String = "Standard",
    val ssid: String = "",
    val vlan: Int = 0,
    val technicianNotes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)