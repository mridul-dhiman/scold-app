package com.scold.app.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "scold_preferences")

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    companion object {
        val KEY_DAILY_LIMIT_SEC = intPreferencesKey("daily_limit_sec")
        val KEY_STRICTNESS_LEVEL = stringPreferencesKey("strictness_level")
        val KEY_USER_ID = stringPreferencesKey("user_id")
        val KEY_OPENS_TODAY = intPreferencesKey("total_opens_today")
        val KEY_LAST_OPEN_DATE = longPreferencesKey("last_open_date")
    }

    val dailyLimitSec: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[KEY_DAILY_LIMIT_SEC] ?: 180 // Default 3 minutes
    }

    val opensToday: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[KEY_OPENS_TODAY] ?: 1
    }

    suspend fun setDailyLimitSec(seconds: Int) {
        context.dataStore.edit { preferences ->
            preferences[KEY_DAILY_LIMIT_SEC] = seconds
        }
    }

    suspend fun incrementOpensToday() {
        context.dataStore.edit { preferences ->
            val current = preferences[KEY_OPENS_TODAY] ?: 0
            preferences[KEY_OPENS_TODAY] = current + 1
            preferences[KEY_LAST_OPEN_DATE] = System.currentTimeMillis()
        }
    }

    suspend fun resetOpensToday() {
        context.dataStore.edit { preferences ->
            preferences[KEY_OPENS_TODAY] = 1
            preferences[KEY_LAST_OPEN_DATE] = System.currentTimeMillis()
        }
    }
}
