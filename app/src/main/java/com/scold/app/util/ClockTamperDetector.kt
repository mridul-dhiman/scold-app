package com.scold.app.util

import android.os.SystemClock

object ClockTamperDetector {
    /**
     * Checks if the user changed the wall clock time.
     * Returns true if there is a severe discrepancy between wall time progression
     * and monotonic hardware elapsed realtime progression.
     */
    fun hasClockDrift(
        recordedWallTime: Long,
        recordedElapsedRealtime: Long,
        thresholdMs: Long = 10_000L
    ): Boolean {
        val wallDelta = System.currentTimeMillis() - recordedWallTime
        val hardwareDelta = SystemClock.elapsedRealtime() - recordedElapsedRealtime
        return kotlin.math.abs(wallDelta - hardwareDelta) > thresholdMs
    }
}
