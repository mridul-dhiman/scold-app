package com.scold.app.ui.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.filled.VideocamOff
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.scold.app.timer.TimerState
import com.scold.app.ui.components.ChipStatus
import com.scold.app.ui.components.PenaltyTextInput
import com.scold.app.ui.components.TelemetryChip

@Composable
fun LockoutOverlayScreen(
    timerState: TimerState,
    onBypassGranted: () -> Unit,
    onExitApp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Intercept back gesture so user cannot dismiss the lockout
    BackHandler(enabled = true) {
        // Intentionally denied
    }

    var isBypassUnlocked by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0C))
            .verticalScroll(rememberScrollState()),
    ) {
        // Harsh Marquee Rail
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF93000A))
                .padding(horizontal = 16.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    tint = Color(0xFFFFDAD6),
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "[LOCKOUT PROTOCOL ACTIVE // TERMINAL SHUTDOWN ENFORCED]",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFFFDAD6),
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "SYS_REV: 04.99.2",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFFFFDAD6)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Stark Statement
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0E0E10))
                    .border(1.dp, Color(0xFF2A2E39), RectangleShape)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "DISCIPLINARY HARD-WALL",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFFF3B30),
                        fontWeight = FontWeight.Bold
                    )
                    TelemetryChip(label = "CRITICAL EXHAUSTION", status = ChipStatus.BREACH)
                }

                Text(
                    text = "YOUR TIME IS UP.\nGO DO SOMETHING REAL.",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFFFF5545)
                )

                Text(
                    text = "Cognitive breach verified. Peripheral interface sockets have been severed. Continuous sensory feed denied.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF8F94A0)
                )
            }

            // Quota Audit Telemetry Module
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF16171B))
                    .border(1.dp, Color(0xFF20232B), RectangleShape)
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "MODULE_07 // QUOTA_AUDIT",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFF59E0B)
                    )
                    TelemetryChip(label = "ZERO_CREDIT", status = ChipStatus.BREACH)
                }

                // 2x2 Telemetry Grid
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(Color(0xFF20232B))
                                .padding(8.dp)
                        ) {
                            Column {
                                Text("DAILY ALLOWANCE", style = MaterialTheme.typography.labelSmall, color = Color(0xFF8F94A0))
                                Text("03m 00s [0%]", style = MaterialTheme.typography.headlineSmall, color = Color(0xFFFF3B30))
                                Text("TOTAL DEPLETED", style = MaterialTheme.typography.bodySmall, color = Color(0xFFE7BDB7))
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(Color(0xFF20232B))
                                .padding(8.dp)
                        ) {
                            Column {
                                Text("EXPOSURE RECORDED", style = MaterialTheme.typography.labelSmall, color = Color(0xFF8F94A0))
                                Text("180.00s", style = MaterialTheme.typography.headlineSmall, color = Color(0xFFEDEDF0))
                                Text("MAX EXCEEDED", style = MaterialTheme.typography.bodySmall, color = Color(0xFFF59E0B))
                            }
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(Color(0xFF20232B))
                                .padding(8.dp)
                        ) {
                            Column {
                                Text("ATTENTION COST", style = MaterialTheme.typography.labelSmall, color = Color(0xFF8F94A0))
                                Text("100% SINK", style = MaterialTheme.typography.headlineSmall, color = Color(0xFFFF3B30))
                                Text("SYNAPSE DRAIN", style = MaterialTheme.typography.bodySmall, color = Color(0xFFE7BDB7))
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(Color(0xFF20232B))
                                .padding(8.dp)
                        ) {
                            Column {
                                Text("NEXT WINDOW IN", style = MaterialTheme.typography.labelSmall, color = Color(0xFF8F94A0))
                                Text("21h 12m", style = MaterialTheme.typography.headlineSmall, color = Color(0xFFFF5545))
                                Text("04:00:00 UTC", style = MaterialTheme.typography.bodySmall, color = Color(0xFF8F94A0))
                            }
                        }
                    }
                }
            }

            // Surveillance Snapshot Panel
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF16171B))
                    .border(1.dp, Color(0xFF20232B), RectangleShape)
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
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
                            imageVector = Icons.Default.VideocamOff,
                            contentDescription = null,
                            tint = Color(0xFFFF3B30),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "[FEED_FROZEN // LOG_LOCKED]",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFEDEDF0)
                        )
                    }
                    TelemetryChip(label = "FRAME #0944-REC", status = ChipStatus.BREACH)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .background(Color(0xFF0E0E10))
                        .border(1.dp, Color(0xFF2A2E39), RectangleShape)
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "[STATIC SENSOR // CAM_01]",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFFF3B30)
                        )
                        Text(
                            text = "MONITOR LOG: RECEPTOR INACTIVITY CONFIRMED",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF8F94A0)
                        )
                    }
                }
            }

            // Cognitive Bypass Valve
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF16171B))
                    .border(1.dp, Color(0xFF20232B), RectangleShape)
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "COGNITIVE BYPASS VALVE",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFF59E0B)
                    )
                    TelemetryChip(label = "STATUS: ACTIVE_DEFENSE", status = ChipStatus.RESTRICTED)
                }

                Text(
                    text = "TYPE THE FOLLOWING PENALTY PHRASE VERBATIM TO BYPASS:",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFEDEDF0)
                )

                PenaltyTextInput(
                    targetPhrase = "I lack self control and need 60 more seconds.",
                    onMatch = {
                        isBypassUnlocked = true
                    }
                )

                Button(
                    onClick = {
                        if (isBypassUnlocked) {
                            onBypassGranted()
                        }
                    },
                    enabled = isBypassUnlocked,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFEE9800),
                        contentColor = Color(0xFF5B3800),
                        disabledContainerColor = Color(0xFF20232B),
                        disabledContentColor = Color(0xFF8F94A0)
                    )
                ) {
                    Text(
                        text = if (isBypassUnlocked) {
                            "EXECUTE 60s EMERGENCY BYPASS (+500ms PENALTY)"
                        } else {
                            "[BYPASS DISABLED - EXACT MATCH REQUIRED]"
                        },
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            // Primary Exit Action
            Button(
                onClick = onExitApp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF5545),
                    contentColor = Color(0xFF5C0002)
                )
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.PowerSettingsNew,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "CLOSE APP IMMEDIATELY",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }

            // Regulation Notice
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0E0E10))
                    .border(1.dp, Color(0xFF2A2E39), RectangleShape)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Gavel,
                    contentDescription = null,
                    tint = Color(0xFFF59E0B),
                    modifier = Modifier.size(20.dp)
                )
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "MANDATORY SYSTEM REGULATION",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFF59E0B)
                    )
                    Text(
                        text = "REPEATED BYPASS WILL INCUR MANDATORY 500MS LATENCY PENALTY ON FUTURE SESSIONS. PERSISTENT TAMPERING RESULTS IN COMPLETE 72-HOUR REGISTRATION NULLIFICATION.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFE7BDB7)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
