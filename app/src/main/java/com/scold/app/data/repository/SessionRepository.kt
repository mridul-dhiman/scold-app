package com.scold.app.data.repository

import com.scold.app.data.local.SessionDao
import com.scold.app.data.local.SessionEntity
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

interface SessionRepository {
    suspend fun startSession(userId: String, dailyLimitSec: Int): SessionEntity
    suspend fun getCurrentSession(): SessionEntity?
    suspend fun endSession(sessionId: String)
    suspend fun logBypass(sessionId: String)
    fun getTodaySessions(): Flow<List<SessionEntity>>
    fun getAllSessions(): Flow<List<SessionEntity>>
    suspend fun getAccumulatedTimeToday(): Int
}

@Singleton
class SessionRepositoryImpl @Inject constructor(
    private val sessionDao: SessionDao,
) : SessionRepository {

    override suspend fun startSession(userId: String, dailyLimitSec: Int): SessionEntity {
        val now = System.currentTimeMillis()
        val session = SessionEntity(
            sessionId = UUID.randomUUID().toString(),
            userId = userId,
            startTimestamp = now,
            dailyLimitSec = dailyLimitSec,
            totalSecToday = getAccumulatedTimeToday(),
            bypassCount = 0,
            wasBypassed = false,
            endTimestamp = null,
        )
        sessionDao.insertSession(session)
        return session
    }

    override suspend fun getCurrentSession(): SessionEntity? =
        sessionDao.getCurrentActiveSession()

    override suspend fun endSession(sessionId: String) {
        val current = sessionDao.getCurrentActiveSession()
        if (current != null && current.sessionId == sessionId) {
            sessionDao.updateSession(current.copy(endTimestamp = System.currentTimeMillis()))
        }
    }

    override suspend fun logBypass(sessionId: String) {
        val current = sessionDao.getCurrentActiveSession()
        if (current != null) {
            sessionDao.updateSession(
                current.copy(
                    bypassCount = current.bypassCount + 1,
                    wasBypassed = true,
                )
            )
        }
    }

    override fun getTodaySessions(): Flow<List<SessionEntity>> {
        val midnight = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
        return sessionDao.getSessionsForDate(midnight)
    }

    override fun getAllSessions(): Flow<List<SessionEntity>> =
        sessionDao.getAllSessions()

    override suspend fun getAccumulatedTimeToday(): Int {
        val sessions = sessionDao.getRecentSessions()
        val midnight = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
        return sessions
            .filter { it.startTimestamp >= midnight }
            .sumOf {
                val end = it.endTimestamp ?: System.currentTimeMillis()
                ((end - it.startTimestamp) / 1000).toInt()
            }
    }
}
