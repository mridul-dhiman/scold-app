package com.scold.app.ui.screens

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.scold.app.ui.components.ChipStatus
import com.scold.app.ui.components.TelemetryChip

@Composable
fun AnalyticsScreen(
    modifier: Modifier = Modifier,
) {
    val dailyMinutes = listOf(2.8f, 3.1f, 1.9f, 4.2f, 3.0f, 2.5f, 3.0f)
    val days = listOf("M", "T", "W", "T", "F", "S", "S")
    val dailyLimit = 3.0f

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0C))
            .verticalScroll(rememberScrollState()),
    ) {
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
                    text = "// ANALYTICS",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF8F94A0)
                )
                Text(
                    text = "BEHAVIORAL DIAGNOSTICS",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFFEDEDF0)
                )
            }
            TelemetryChip(label = "PEER AUDITED", status = ChipStatus.CONTAINED)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Today Summary Module
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF16171B))
                    .border(1.dp, Color(0xFF20232B), RectangleShape)
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "TODAY'S DISCIPLINARY LEDGER",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFF59E0B)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color(0xFF20232B))
                            .padding(10.dp)
                    ) {
                        Column {
                            Text("TOTAL USAGE", style = MaterialTheme.typography.labelSmall, color = Color(0xFF8F94A0))
                            Text("03m 00s", style = MaterialTheme.typography.headlineSmall, color = Color(0xFFFF5545))
                            Text("100% DEPLETED", style = MaterialTheme.typography.bodySmall, color = Color(0xFFE7BDB7))
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color(0xFF20232B))
                            .padding(10.dp)
                    ) {
                        Column {
                            Text("SESSIONS OPENED", style = MaterialTheme.typography.labelSmall, color = Color(0xFF8F94A0))
                            Text("4 OPENS", style = MaterialTheme.typography.headlineSmall, color = Color(0xFFEDEDF0))
                            Text("HIGH COMPULSION", style = MaterialTheme.typography.bodySmall, color = Color(0xFFF59E0B))
                        }
                    }
                }
            }

            // Weekly Trend Bar Chart
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
                        text = "WEEKLY EXPOSURE (MINUTES)",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF8F94A0)
                    )
                    Text(
                        text = "HARD LIMIT: 3.0M",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFFF3B30)
                    )
                }

                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .background(Color(0xFF0E0E10))
                        .padding(vertical = 12.dp, horizontal = 16.dp)
                ) {
                    val maxVal = 5.0f
                    val barWidth = 24.dp.toPx()
                    val spacing = (size.width - (barWidth * dailyMinutes.size)) / (dailyMinutes.size + 1)
                    val limitY = size.height * (1f - (dailyLimit / maxVal))

                    // Draw limit line
                    drawLine(
                        color = Color(0xFFFF3B30),
                        start = Offset(0f, limitY),
                        end = Offset(size.width, limitY),
                        strokeWidth = 2.dp.toPx()
                    )

                    dailyMinutes.forEachIndexed { i, value ->
                        val barHeight = size.height * (value / maxVal)
                        val x = spacing + i * (barWidth + spacing)
                        val y = size.height - barHeight
                        val isOverLimit = value > dailyLimit

                        drawRect(
                            color = if (isOverLimit) Color(0xFFFF3B30) else Color(0xFF2A2E39),
                            topLeft = Offset(x, y),
                            size = Size(barWidth, barHeight)
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    days.forEach { day ->
                        Text(
                            text = day,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF8F94A0)
                        )
                    }
                }
            }

            // Session History Log
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF16171B))
                    .border(1.dp, Color(0xFF20232B), RectangleShape)
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "IMMUTABLE AUDIT LOG",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFF59E0B)
                )

                val sampleLogs = listOf(
                    Triple("12:44:10", "180s DURATION", "EXHAUSTED"),
                    Triple("11:15:02", "42s DURATION", "VOLUNTARY EXIT"),
                    Triple("09:30:19", "60s DURATION", "BYPASS GRANTED"),
                )

                sampleLogs.forEach { (time, dur, outcome) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF0E0E10))
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(time, style = MaterialTheme.typography.labelSmall, color = Color(0xFFEDEDF0))
                            Text(dur, style = MaterialTheme.typography.bodySmall, color = Color(0xFF8F94A0))
                        }
                        TelemetryChip(
                            label = outcome,
                            status = if (outcome.contains("EXHAUSTED") || outcome.contains("BYPASS")) ChipStatus.BREACH else ChipStatus.CONTAINED
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
