package com.pandey.isptoolkit.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ont_readings")
data class OntReadingEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val siteId: Long = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val ponStatus: String,
    val losStatus: String,
    val rxPowerDbm: Double,
    val txPowerDbm: Double,
    val temperatureC: Double,
    val voltageV: Double,
    val onuSerial: String,
    val model: String,
    val oltPortNote: String,
    val vlan: Int,
    val technicianNote: String
)