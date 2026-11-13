package com.scold.app.ui.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.scold.app.data.repository.Post
import com.scold.app.timer.DegradationLevel
import com.scold.app.timer.TimerState
import com.scold.app.ui.components.ChipStatus
import com.scold.app.ui.components.FeedCard
import com.scold.app.ui.components.HoldToConfirmButton
import com.scold.app.ui.components.RedactedMediaCard
import com.scold.app.ui.components.StickyTimerBanner
import com.scold.app.ui.components.TelemetryChip

@Composable
fun FeedScreen(
    timerState: TimerState,
    onNavigateToCompose: () -> Unit,
    onNavigateToMathUnlock: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val activity = LocalContext.current as? Activity

    var posts by remember {
        mutableStateOf(
            listOf(
                Post(
                    id = "4902",
                    userId = "user1",
                    username = "marcus_w",
                    content = "Ceased dopamine loop after 42 seconds of mindless shelf-refreshing. Real work resumes now. Your glance here is logged under peer ledger.",
                    createdAt = System.currentTimeMillis() - 840000,
                    guiltScore = 14,
                    isAcknowledged = false,
                ),
                Post(
                    id = "4903",
                    userId = "user2",
                    username = "j_nakamura",
                    content = "Day 12 of using this hostile terminal. Haven't opened algorithmic feeds in 9 days. The shame typing bypass is genuinely too annoying to bother with.",
                    createdAt = System.currentTimeMillis() - 3600000,
                    guiltScore = 8,
                    isAcknowledged = false,
                ),
                Post(
                    id = "4904",
                    userId = "user3",
                    username = "clara_dev",
                    content = "Shipped a critical production fix instead of scrolling. 47 minutes reclaimed. This hostile UX actually works on me.",
                    createdAt = System.currentTimeMillis() - 7200000,
                    guiltScore = 5,
                    isAcknowledged = false,
                )
            )
        )
    }

    val isDimming = timerState.degradationLevel == DegradationLevel.DIMMING || timerState.degradationLevel == DegradationLevel.CRITICAL

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0C))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color(0xFF0E0E10))
                    .border(1.dp, Color(0xFF2A2E39), RectangleShape)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "// SCOLD OS v4.09",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF8F94A0)
                    )
                    Text(
                        text = "FEED",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color(0xFFEDEDF0)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val minutes = timerState.remainingSeconds / 60
                    val seconds = timerState.remainingSeconds % 60
                    TelemetryChip(
                        label = "-%02d:%02d".format(minutes, seconds),
                        status = if (isDimming) ChipStatus.BREACH else ChipStatus.CONTAINED
                    )

                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color(0xFFFFB4AA), RectangleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color(0xFF690003),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            // Sticky timer
            StickyTimerBanner(
                remainingSeconds = timerState.remainingSeconds,
                totalSeconds = timerState.totalAllowedSeconds,
                degradationLevel = timerState.degradationLevel
            )

            // Feed Content
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    // Telemetry Harness
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF16171B))
                            .border(1.dp, Color(0xFF20232B), RectangleShape)
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "// TELEMETRY HARNESS 09-B",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF8F94A0)
                            )
                            TelemetryChip(label = "LIVE ENFORCEMENT", status = ChipStatus.RESTRICTED)
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            TelemetryChip(label = "SCRUTINY: SEVERE", status = ChipStatus.BREACH)
                            TelemetryChip(label = "COGNITIVE TAX: 82%", status = ChipStatus.RESTRICTED)
                        }
                    }
                }

                // Feed Cards
                items(posts) { post ->
                    FeedCard(
                        post = post,
                        onAcknowledge = { id ->
                            posts = posts.map { if (it.id == id) it.copy(isAcknowledged = true) else it }
                        }
                    )
                }

                // Redacted media card
                item {
                    RedactedMediaCard(
                        username = "t_rivers",
                        trivialityIndex = 94,
                        stimIndex = 89,
                        onUnlockRequested = onNavigateToMathUnlock
                    )
                }

                // Daylight intervention card
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF16171B))
                            .border(1.dp, Color(0xFF2A2E39), RectangleShape)
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.LightMode,
                                    contentDescription = null,
                                    tint = Color(0xFFFF5545),
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = "// DAYLIGHT CONTAINMENT INTERVENTION",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFFFF5545)
                                )
                            }
                            Text(
                                text = "INCIDENT #772",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF8F94A0)
                            )
                        }

                        Text(
                            text = "WHY ARE YOU STILL SCROLLING IN BROAD DAYLIGHT?",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color(0xFFEDEDF0)
                        )

                        Text(
                            text = "Natural illumination detected. Your physiological circadian cadence is being hijacked by low-grade passive updates. Turn your device screen-down on the nearest hard surface immediately.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF8F94A0)
                        )
                    }
                }

                // Hold to confirm button
                item {
                    HoldToConfirmButton(
                        label = "HOLD 3.0s TO LOCK TERMINAL AND RESUME WORK",
                        onConfirmed = {
                            activity?.finishAndRemoveTask()
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }

        // Post Quota FAB
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xFF0E0E10))
                    .border(1.dp, Color(0xFF2A2E39), RectangleShape)
                    .padding(horizontal = 12.dp, vertical = 14.dp)
            ) {
                Text(
                    text = "[QUOTA: 1/1 REMAINING]",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFEDEDF0)
                )
            }

            FloatingActionButton(
                onClick = onNavigateToCompose,
                shape = RectangleShape,
                containerColor = Color(0xFFFF5545),
                contentColor = Color(0xFF5C0002)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Create Post")
            }
        }
    }
}
