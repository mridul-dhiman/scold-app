package com.scold.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.scold.app.ui.components.ChipStatus
import com.scold.app.ui.components.TelemetryChip

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
) {
    var limitMinutes by remember { mutableFloatStateOf(3f) }
    var selectedStrictness by remember { mutableStateOf("SEVERE") }
    val strictnessOptions = listOf("LOW", "MEDIUM", "SEVERE", "MAXIMUM")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0C))
            .verticalScroll(rememberScrollState()),
    ) {
        // Top Bar
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
                    text = "// SETTINGS",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF8F94A0)
                )
                Text(
                    text = "ENFORCEMENT CONFIG",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFFEDEDF0)
                )
            }
            TelemetryChip(label = "HARD-LOCKED", status = ChipStatus.BREACH)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Daily Limit Slider
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
                        text = "DAILY ALLOCATION LIMIT",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF8F94A0)
                    )
                    Text(
                        text = "${limitMinutes.toInt()} MINUTES / DAY",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFFF5545)
                    )
                }

                Slider(
                    value = limitMinutes,
                    onValueChange = { limitMinutes = it },
                    valueRange = 1f..10f,
                    steps = 8,
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0xFFFF5545),
                        activeTrackColor = Color(0xFFFF5545),
                        inactiveTrackColor = Color(0xFF2A2E39)
                    )
                )

                Text(
                    text = "Lower allocations induce higher cognitive resistance. Increasing allocation is limited to once every 24 hours.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF8F94A0)
                )
            }

            // Strictness Level Selector
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF16171B))
                    .border(1.dp, Color(0xFF20232B), RectangleShape)
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "DISCIPLINARY STRICTNESS HARNESS",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFF59E0B)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    strictnessOptions.forEach { opt ->
                        val isSelected = selectedStrictness == opt
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                                .background(if (isSelected) Color(0xFFFF5545) else Color(0xFF20232B), RectangleShape)
                                .border(1.dp, if (isSelected) Color(0xFFFF5545) else Color(0xFF2A2E39), RectangleShape)
                                .clickable { selectedStrictness = opt },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = opt,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (isSelected) Color(0xFF5C0002) else Color(0xFFEDEDF0)
                            )
                        }
                    }
                }
            }

            // Trust & Privacy Guarantee
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0E0E10))
                    .border(1.dp, Color(0xFF2A2E39), RectangleShape)
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "TRUST SIGNAL & PURPOSE MANIFESTO",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFEDEDF0)
                )
                Text(
                    text = "We track your session time to punish you and protect your attention, not to sell your telemetry to advertisers. There are zero targeted algorithms, zero endless feeds, and zero engagement maximization engines running in this binary.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF8F94A0)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
