package com.scold.app.analytics

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScoldAnalytics @Inject constructor() {
    companion object {
        private const val TAG = "ScoldAnalytics"
    }

    fun logEvent(name: String, params: Map<String, Any> = emptyMap()) {
        val paramString = params.entries.joinToString(", ") { "${it.key}=${it.value}" }
        Log.i(TAG, "[$name] $paramString")
    }

    fun logAppOpen(opensToday: Int) = logEvent("app_open", mapOf("opens_today" to opensToday))
    fun logGuiltGateAccepted() = logEvent("guilt_gate_accepted")
    fun logTimerExpired(duration: Int) = logEvent("timer_expired", mapOf("duration_sec" to duration))
    fun logBypassAttempted(method: String) = logEvent("bypass_attempted", mapOf("method" to method))
    fun logBypassFailed(reason: String) = logEvent("bypass_failed", mapOf("reason" to reason))
    fun logBypassSuccessful(extraSeconds: Int) = logEvent("bypass_successful", mapOf("extra_seconds" to extraSeconds))
    fun logSessionEnded(duration: Int, wasVoluntary: Boolean) = logEvent("session_ended", mapOf("duration" to duration, "voluntary" to wasVoluntary))
    fun logMediaUnlockAttempted(stimIndex: Int) = logEvent("media_unlock_attempted", mapOf("stim_index" to stimIndex))
}
