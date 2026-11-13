package com.scold.app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CautionStripeBackground(
    modifier: Modifier = Modifier,
    stripeColor: Color = Color(0xFFFF3B30),
    backgroundColor: Color = Color(0xFF0A0A0C),
    stripeWidthDp: Float = 14f,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier.drawBehind {
            drawRect(color = backgroundColor)
            val pxWidth = stripeWidthDp * density
            val angleRad = Math.toRadians(45.0).toFloat()
            val step = pxWidth * 2

            var x = -size.height
            while (x < size.width + size.height) {
                drawLine(
                    color = stripeColor,
                    start = Offset(x, 0f),
                    end = Offset(x + size.height, size.height),
                    strokeWidth = pxWidth,
                )
                x += step
            }
        }
    ) {
        content()
    }
}
