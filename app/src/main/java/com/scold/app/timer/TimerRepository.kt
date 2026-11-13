package com.scold.app.timer

import com.scold.app.data.repository.SessionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimerRepository @Inject constructor(
    private val sessionRepository: SessionRepository,
) {
    suspend fun startSession(userId: String, dailyLimitSec: Int) =
        sessionRepository.startSession(userId, dailyLimitSec)

    suspend fun getCurrentSession() =
        sessionRepository.getCurrentSession()

    suspend fun endSession(sessionId: String) =
        sessionRepository.endSession(sessionId)

    suspend fun logBypass(sessionId: String) =
        sessionRepository.logBypass(sessionId)

    fun getTodaySessions() =
        sessionRepository.getTodaySessions()

    suspend fun getAccumulatedTimeToday() =
        sessionRepository.getAccumulatedTimeToday()
}
