package com.pandey.isptoolkit.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pandey.isptoolkit.data.local.dao.DeviceDao
import com.pandey.isptoolkit.data.local.dao.DiagnosticDao
import com.pandey.isptoolkit.data.local.dao.OntDao
import com.pandey.isptoolkit.data.local.dao.SiteDao
import com.pandey.isptoolkit.data.local.entity.DeviceEntity
import com.pandey.isptoolkit.data.local.entity.DiagnosticSessionEntity
import com.pandey.isptoolkit.data.local.entity.OntReadingEntity
import com.pandey.isptoolkit.data.local.entity.SiteEntity
import com.pandey.isptoolkit.data.local.entity.VisitEntity

@Database(
    entities = [
        SiteEntity::class,
        VisitEntity::class,
        DeviceEntity::class,
        OntReadingEntity::class,
        DiagnosticSessionEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun siteDao(): SiteDao
    abstract fun deviceDao(): DeviceDao
    abstract fun ontDao(): OntDao
    abstract fun diagnosticDao(): DiagnosticDao
}