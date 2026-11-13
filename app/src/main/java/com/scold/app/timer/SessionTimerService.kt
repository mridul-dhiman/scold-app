package com.scold.app.timer

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import com.scold.app.MainActivity
import com.scold.app.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class SessionTimerService : Service() {

    private val serviceScope = CoroutineScope(Dispatchers.Default + Job())
    private var tickerJob: Job? = null

    companion object {
        private const val CHANNEL_ID = "scold_session_timer_channel"
        private const val NOTIFICATION_ID = 9044

        private val _timerState = MutableStateFlow(
            TimerState(
                remainingSeconds = 180,
                totalAllowedSeconds = 180,
                isExpired = false,
                degradationLevel = DegradationLevel.NORMAL,
                sessionStartTimestamp = System.currentTimeMillis(),
                bypasses = 0,
                bypassPenaltySeconds = 0,
            )
        )
        val timerState: StateFlow<TimerState> = _timerState.asStateFlow()

        private var sessionStartWallTime = System.currentTimeMillis()
        private var sessionStartElapsedRealtime = SystemClock.elapsedRealtime()
        private var allocatedSeconds = 180
        private var bypassCount = 0
        private var penaltySeconds = 0

        fun startSession(context: Context, totalSeconds: Int = 180) {
            allocatedSeconds = totalSeconds
            sessionStartWallTime = System.currentTimeMillis()
            sessionStartElapsedRealtime = SystemClock.elapsedRealtime()
            bypassCount = 0
            penaltySeconds = 0

            _timerState.value = TimerState(
                remainingSeconds = totalSeconds,
                totalAllowedSeconds = totalSeconds,
                isExpired = false,
                degradationLevel = DegradationLevel.NORMAL,
                sessionStartTimestamp = sessionStartWallTime,
                bypasses = 0,
                bypassPenaltySeconds = 0,
            )

            val intent = Intent(context, SessionTimerService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }

        fun grantBypass(extraSeconds: Int = 60) {
            bypassCount += 1
            penaltySeconds += 30 // Penalize future sessions
            allocatedSeconds += extraSeconds

            val currentRemaining = _timerState.value.remainingSeconds.coerceAtLeast(0)
            val newRemaining = currentRemaining + extraSeconds

            _timerState.value = _timerState.value.copy(
                remainingSeconds = newRemaining,
                totalAllowedSeconds = allocatedSeconds,
                isExpired = false,
                degradationLevel = calculateDegradation(newRemaining),
                bypasses = bypassCount,
                bypassPenaltySeconds = penaltySeconds,
            )
        }

        private fun calculateDegradation(remaining: Int): DegradationLevel {
            return when {
                remaining <= 0 -> DegradationLevel.EXPIRED
                remaining <= 10 -> DegradationLevel.CRITICAL
                remaining <= 60 -> DegradationLevel.DIMMING
                else -> DegradationLevel.NORMAL
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, buildNotification("Session Enforcing: ${_timerState.value.remainingSeconds}s remaining"))
        startTicker()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun startTicker() {
        tickerJob?.cancel()
        tickerJob = serviceScope.launch {
            while (isActive) {
                delay(1000L)
                val nowElapsed = SystemClock.elapsedRealtime()
                val elapsedSeconds = ((nowElapsed - sessionStartElapsedRealtime) / 1000).toInt()
                val remaining = (allocatedSeconds - elapsedSeconds).coerceAtLeast(0)

                val degradation = calculateDegradation(remaining)
                val isExpired = remaining <= 0

                _timerState.value = _timerState.value.copy(
                    remainingSeconds = remaining,
                    isExpired = isExpired,
                    degradationLevel = degradation,
                )

                updateNotification("$remaining s remaining // Hostile Enforcement Active")
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                getString(R.string.notification_channel_timer),
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = getString(R.string.notification_channel_timer_desc)
                setShowBadge(false)
            }
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }
    }

    private fun buildNotification(text: String): Notification {
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("SCOLD // TERMINAL WATCHDOG")
            .setContentText(text)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .build()
    }

    private fun updateNotification(text: String) {
        val manager = getSystemService(NotificationManager::class.java)
        manager?.notify(NOTIFICATION_ID, buildNotification(text))
    }

    override fun onDestroy() {
        tickerJob?.cancel()
        super.onDestroy()
    }
}
