package com.pandey.isptoolkit.data.local.dao

import androidx.room.*
import com.pandey.isptoolkit.data.local.entity.DeviceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDao {
    @Query("SELECT * FROM devices ORDER BY lastSeen DESC")
    fun getAllDevices(): Flow<List<DeviceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDevices(devices: List<DeviceEntity>)

    @Query("UPDATE devices SET isSaved = :isSaved, userNote = :note WHERE ipAddress = :ip")
    suspend fun updateDeviceSavedState(ip: String, isSaved: Boolean, note: String)
}