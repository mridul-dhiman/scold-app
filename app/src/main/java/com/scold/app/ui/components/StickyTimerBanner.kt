package com.scold.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.scold.app.timer.DegradationLevel

@Composable
fun StickyTimerBanner(
    remainingSeconds: Int,
    totalSeconds: Int,
    degradationLevel: DegradationLevel,
    modifier: Modifier = Modifier,
) {
    val minutes = remainingSeconds / 60
    val seconds = remainingSeconds % 60
    val timerText = "%02d:%02d".format(minutes, seconds)

    val timerColor = when (degradationLevel) {
        DegradationLevel.CRITICAL -> Color(0xFFFF3B30)
        DegradationLevel.DIMMING -> Color(0xFFF59E0B)
        else -> Color(0xFFEDEDF0)
    }

    val elapsedFraction = if (totalSeconds > 0) {
        (totalSeconds - remainingSeconds).toFloat() / totalSeconds.toFloat()
    } else 1f

    val totalSegments = 6
    val filledCount = (elapsedFraction * totalSegments).toInt().coerceIn(0, totalSegments)
    val segments = (0 until totalSegments).map { index ->
        MeterSegment(
            weight = 1f,
            isFilled = index < filledCount,
            isActive = index == (filledCount - 1).coerceAtLeast(0),
            color = if (degradationLevel == DegradationLevel.CRITICAL) Color(0xFFFF3B30) else Color(0xFFFF5545)
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF0E0E10))
            .border(1.dp, Color(0xFF2A2E39), RectangleShape)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = timerText,
                    style = MaterialTheme.typography.headlineMedium,
                    color = timerColor,
                )
                Text(
                    text = " [DEPLETION RATE: 1.0X]",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF8F94A0),
                    modifier = Modifier.padding(start = 6.dp)
                )
            }

            TelemetryChip(
                label = if (degradationLevel == DegradationLevel.CRITICAL) "CRITICAL TAX" else "PUNITIVE TAX ACTIVE",
                status = if (degradationLevel == DegradationLevel.CRITICAL) ChipStatus.BREACH else ChipStatus.RESTRICTED
            )
        }

        SegmentedMeterBar(segments = segments)
    }
}
