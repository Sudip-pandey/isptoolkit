package com.pandey.isptoolkit.data.local.dao

import androidx.room.*
import com.pandey.isptoolkit.data.local.entity.DiagnosticSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DiagnosticDao {
    @Query("SELECT * FROM diagnostic_sessions ORDER BY timestamp DESC")
    fun getAllSessions(): Flow<List<DiagnosticSessionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: DiagnosticSessionEntity): Long
}