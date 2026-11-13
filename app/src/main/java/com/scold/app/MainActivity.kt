package com.scold.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.scold.app.navigation.ScoldNavGraph
import com.scold.app.timer.DegradationLevel
import com.scold.app.timer.SessionTimerService
import com.scold.app.timer.TimerState
import com.scold.app.ui.screens.LockoutOverlayScreen
import com.scold.app.ui.theme.ScoldTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ScoldTheme {
                val timerState by SessionTimerService.timerState.collectAsStateWithLifecycle(
                    initialValue = TimerState(
                        remainingSeconds = 180,
                        totalAllowedSeconds = 180,
                        isExpired = false,
                        degradationLevel = DegradationLevel.NORMAL,
                        sessionStartTimestamp = 0L,
                        bypasses = 0,
                        bypassPenaltySeconds = 0,
                    )
                )

                Box(modifier = Modifier.fillMaxSize()) {
                    // Main navigation underneath
                    ScoldNavGraph(timerState = timerState)

                    // Lockout overlay ON TOP when expired
                    if (timerState.isExpired) {
                        LockoutOverlayScreen(
                            timerState = timerState,
                            onBypassGranted = {
                                SessionTimerService.grantBypass()
                            },
                            onExitApp = { finishAndRemoveTask() },
                        )
                    }
                }
            }
        }
    }
}
