package com.scold.app.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

data class MeterSegment(
    val weight: Float = 1f,
    val isFilled: Boolean,
    val isActive: Boolean = false,
    val color: Color = Color(0xFFFF3B30),
)

@Composable
fun SegmentedMeterBar(
    segments: List<MeterSegment>,
    modifier: Modifier = Modifier,
) {
    val transition = rememberInfiniteTransition(label = "pulse")
    val pulseAlpha by transition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 600),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(8.dp)
            .background(Color(0xFF16171C), RectangleShape),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        segments.forEach { segment ->
            val segmentAlpha = if (segment.isActive) pulseAlpha else 1.0f
            val segmentColor = if (segment.isFilled) segment.color else Color(0xFF20232B)

            Box(
                modifier = Modifier
                    .weight(segment.weight)
                    .fillMaxHeight()
                    .alpha(segmentAlpha)
                    .background(segmentColor, RectangleShape)
            )
        }
    }
}
