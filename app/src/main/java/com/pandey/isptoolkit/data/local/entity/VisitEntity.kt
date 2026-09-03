package com.pandey.isptoolkit.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "visits")
data class VisitEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val siteId: Long,
    val timestamp: Long = System.currentTimeMillis(),
    val rssiBefore: Int = 0,
    val rssiAfter: Int = 0,
    val latencyBeforeMs: Long = 0,
    val latencyAfterMs: Long = 0,
    val downloadBeforeMbps: Double = 0.0,
    val downloadAfterMbps: Double = 0.0,
    val notes: String = ""
)