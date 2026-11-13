package com.scold.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: SessionEntity)

    @Update
    suspend fun updateSession(session: SessionEntity)

    @Query("SELECT * FROM sessions WHERE endTimestamp IS NULL ORDER BY startTimestamp DESC LIMIT 1")
    suspend fun getCurrentActiveSession(): SessionEntity?

    @Query("SELECT * FROM sessions WHERE startTimestamp >= :startOfDayTimestamp ORDER BY startTimestamp DESC")
    fun getSessionsForDate(startOfDayTimestamp: Long): Flow<List<SessionEntity>>

    @Query("SELECT * FROM sessions ORDER BY startTimestamp DESC")
    fun getAllSessions(): Flow<List<SessionEntity>>

    @Query("SELECT * FROM sessions ORDER BY startTimestamp DESC LIMIT 50")
    suspend fun getRecentSessions(): List<SessionEntity>
}
