package com.scold.app.navigation

import android.app.Activity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.scold.app.timer.TimerState
import com.scold.app.ui.components.BottomNavBar
import com.scold.app.ui.screens.AnalyticsScreen
import com.scold.app.ui.screens.ComposePostScreen
import com.scold.app.ui.screens.FeedScreen
import com.scold.app.ui.screens.GuiltGateScreen
import com.scold.app.ui.screens.MathUnlockScreen
import com.scold.app.ui.screens.SettingsScreen

object ScoldRoutes {
    const val GUILT_GATE = "guilt_gate"
    const val FEED = "feed"
    const val POST = "post"
    const val STATS = "stats"
    const val SETTINGS = "settings"
    const val MATH_UNLOCK = "math_unlock"
}

private val BOTTOM_NAV_ROUTES = setOf(
    ScoldRoutes.FEED,
    ScoldRoutes.STATS,
    ScoldRoutes.SETTINGS,
)

@Composable
fun ScoldNavGraph(
    timerState: TimerState,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ScoldRoutes.GUILT_GATE
    val activity = LocalContext.current as? Activity

    val showBottomBar = currentRoute in BOTTOM_NAV_ROUTES

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        if (route == "exit") {
                            activity?.finishAndRemoveTask()
                        } else {
                            navController.navigate(route) {
                                popUpTo(ScoldRoutes.FEED) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                )
            }
        },
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScoldRoutes.GUILT_GATE,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(ScoldRoutes.GUILT_GATE) {
                GuiltGateScreen(
                    onAccepted = {
                        navController.navigate(ScoldRoutes.FEED) {
                            popUpTo(ScoldRoutes.GUILT_GATE) { inclusive = true }
                        }
                    },
                )
            }

            composable(ScoldRoutes.FEED) {
                FeedScreen(
                    timerState = timerState,
                    onNavigateToCompose = {
                        navController.navigate(ScoldRoutes.POST)
                    },
                    onNavigateToMathUnlock = {
                        navController.navigate(ScoldRoutes.MATH_UNLOCK)
                    },
                )
            }

            composable(ScoldRoutes.POST) {
                ComposePostScreen(
                    onPostSubmitted = {
                        navController.popBackStack()
                    },
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                )
            }

            composable(ScoldRoutes.STATS) {
                AnalyticsScreen()
            }

            composable(ScoldRoutes.SETTINGS) {
                SettingsScreen()
            }

            composable(ScoldRoutes.MATH_UNLOCK) {
                MathUnlockScreen(
                    onResist = {
                        navController.popBackStack()
                    },
                    onUnlocked = {
                        navController.popBackStack()
                    },
                )
            }
        }
    }
}
