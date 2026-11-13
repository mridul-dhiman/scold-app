package com.scold.app.ui.theme

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Draws a solid-color offset shadow behind the composable — no blur.
 *
 * Usage: `Modifier.hardOffsetShadow(Color(0xFFFF3B30), 2.dp, 2.dp)`
 */
fun Modifier.hardOffsetShadow(
    color: Color,
    offsetX: Dp = 2.dp,
    offsetY: Dp = 2.dp,
): Modifier = this.then(
    Modifier.drawBehind {
        val ox = offsetX.toPx()
        val oy = offsetY.toPx()
        // Horizontal strip along the bottom
        drawRect(
            color = color,
            topLeft = Offset(ox, size.height),
            size = Size(size.width, oy),
        )
        // Vertical strip along the right
        drawRect(
            color = color,
            topLeft = Offset(size.width, oy),
            size = Size(ox, size.height),
        )
        // Corner pixel
        drawRect(
            color = color,
            topLeft = Offset(size.width, size.height),
            size = Size(ox, oy),
        )
    }
)

/**
 * On press: translate element by 2dp in both axes and collapse the shadow.
 * On release: return to resting position with shadow restored.
 *
 * Pair with [hardOffsetShadow] on the same composable — the shadow offset
 * animates from (2dp,2dp) → (0dp,0dp) in sync with the translation.
 */
fun Modifier.pressableOffset(
    shadowColor: Color = Color(0xFFFF3B30),
    restOffsetX: Dp = 2.dp,
    restOffsetY: Dp = 2.dp,
): Modifier = composed {
    var isPressed by remember { mutableStateOf(false) }

    val animatedOffsetX by animateDpAsState(
        targetValue = if (isPressed) 0.dp else restOffsetX,
        animationSpec = tween(durationMillis = 80),
        label = "shadowOffsetX",
    )
    val animatedOffsetY by animateDpAsState(
        targetValue = if (isPressed) 0.dp else restOffsetY,
        animationSpec = tween(durationMillis = 80),
        label = "shadowOffsetY",
    )
    val translationX by animateDpAsState(
        targetValue = if (isPressed) restOffsetX else 0.dp,
        animationSpec = tween(durationMillis = 80),
        label = "translationX",
    )
    val translationY by animateDpAsState(
        targetValue = if (isPressed) restOffsetY else 0.dp,
        animationSpec = tween(durationMillis = 80),
        label = "translationY",
    )

    val density = LocalDensity.current

    this
        .pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    isPressed = true
                    tryAwaitRelease()
                    isPressed = false
                },
            )
        }
        .graphicsLayer {
            this.translationX = with(density) { translationX.toPx() }
            this.translationY = with(density) { translationY.toPx() }
        }
        .hardOffsetShadow(
            color = shadowColor,
            offsetX = animatedOffsetX,
            offsetY = animatedOffsetY,
        )
}
