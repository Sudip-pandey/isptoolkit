package com.pandey.isptoolkit.data.local.dao

import androidx.room.*
import com.pandey.isptoolkit.data.local.entity.OntReadingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OntDao {
    @Query("SELECT * FROM ont_readings ORDER BY timestamp DESC")
    fun getAllOntReadings(): Flow<List<OntReadingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOntReading(reading: OntReadingEntity): Long
}