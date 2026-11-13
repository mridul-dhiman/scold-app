package com.scold.app.ui.components

import android.view.HapticFeedbackConstants
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.scold.app.ui.theme.LocalScoldExtendedColors
import com.scold.app.ui.theme.LocalScoldSpacing
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HoldToConfirmButton(
    label: String,
    completedLabel: String = "[ TERMINAL SUSPENDED: STAND UP ]",
    holdDurationMs: Long = 3000L,
    onConfirmed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalScoldSpacing.current
    val extColors = LocalScoldExtendedColors.current
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    val view = LocalView.current

    var isHolding by remember { mutableStateOf(false) }
    var isCompleted by remember { mutableStateOf(false) }
    val progress = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    var animationJob by remember { mutableStateOf<Job?>(null) }

    val elapsedSeconds = (progress.value * holdDurationMs / 1000f)
    val totalSeconds = holdDurationMs / 1000f

    LaunchedEffect(isHolding) {
        if (isHolding && !isCompleted) {
            animationJob = scope.launch {
                progress.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = (holdDurationMs * (1f - progress.value)).toInt(),
                        easing = LinearEasing,
                    ),
                )
                // Completed
                isCompleted = true
                view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                onConfirmed()
            }
        } else if (!isHolding && !isCompleted) {
            animationJob?.cancel()
            if (progress.value > 0f) {
                view.performHapticFeedback(HapticFeedbackConstants.REJECT)
            }
            progress.snapTo(0f)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colors.surfaceContainerLowest,
                shape = RectangleShape,
            )
            .pointerInput(isCompleted) {
                if (isCompleted) return@pointerInput
                awaitPointerEventScope {
                    while (true) {
                        val down = awaitPointerEvent()
                        if (down.changes.any { it.pressed }) {
                            view.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                            isHolding = true
                            // Wait for release
                            while (true) {
                                val event = awaitPointerEvent()
                                if (event.changes.none { it.pressed }) {
                                    isHolding = false
                                    break
                                }
                            }
                        }
                    }
                }
            }
            .padding(horizontal = spacing.spaceMd, vertical = spacing.spaceLg),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Progress bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(colors.surfaceContainerHighest, RectangleShape),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress.value)
                    .height(8.dp)
                    .background(Color(0xFFF59E0B), RectangleShape),
            )
        }

        // Timer readout
        Text(
            text = "%.1fs / %.1fs".format(elapsedSeconds, totalSeconds),
            style = typography.labelSmall,
            color = Color(0xFF8F94A0),
            modifier = Modifier.padding(top = spacing.spaceXs),
            textAlign = TextAlign.Center,
        )

        // Label
        Text(
            text = if (isCompleted) completedLabel.uppercase() else label.uppercase(),
            style = typography.labelMedium,
            color = if (isCompleted) Color(0xFFF59E0B) else colors.onSurface,
            modifier = Modifier.padding(top = spacing.spaceSm),
            textAlign = TextAlign.Center,
            letterSpacing = typography.labelMedium.letterSpacing,
        )
    }
}
