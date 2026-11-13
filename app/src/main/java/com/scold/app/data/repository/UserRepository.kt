package com.scold.app.data.repository

import com.scold.app.data.local.PreferencesManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface UserRepository {
    fun getDailyLimit(): Flow<Int>
    suspend fun setDailyLimit(seconds: Int)
    fun getOpensToday(): Flow<Int>
    suspend fun incrementOpenCount()
    suspend fun getCurrentUserId(): String
}

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val preferencesManager: PreferencesManager,
) : UserRepository {

    override fun getDailyLimit(): Flow<Int> = preferencesManager.dailyLimitSec

    override suspend fun setDailyLimit(seconds: Int) {
        preferencesManager.setDailyLimitSec(seconds)
    }

    override fun getOpensToday(): Flow<Int> = preferencesManager.opensToday

    override suspend fun incrementOpenCount() {
        preferencesManager.incrementOpensToday()
    }

    override suspend fun getCurrentUserId(): String = "operator_01"
}
