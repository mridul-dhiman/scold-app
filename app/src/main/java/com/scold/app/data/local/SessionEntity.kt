package com.scold.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sessions")
data class SessionEntity(
    @PrimaryKey
    val sessionId: String,
    val userId: String,
    val startTimestamp: Long,
    val dailyLimitSec: Int,
    val totalSecToday: Int,
    val bypassCount: Int,
    val wasBypassed: Boolean,
    val endTimestamp: Long? = null,
)
