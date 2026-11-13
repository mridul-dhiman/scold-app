package com.scold.app.timer

data class TimerState(
    val remainingSeconds: Int,
    val totalAllowedSeconds: Int,
    val isExpired: Boolean,
    val degradationLevel: DegradationLevel,
    val sessionStartTimestamp: Long,
    val bypasses: Int,
    val bypassPenaltySeconds: Int,
)

enum class DegradationLevel {
    /** > 60 s remaining */
    NORMAL,
    /** ≤ 60 s remaining */
    DIMMING,
    /** ≤ 10 s remaining */
    CRITICAL,
    /** ≤ 0 s remaining */
    EXPIRED,
}
