package com.scold.app.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class ScoldSpacing(
    val spaceXs: Dp = 4.dp,
    val spaceSm: Dp = 8.dp,
    val spaceMd: Dp = 16.dp,
    val spaceLg: Dp = 24.dp,
    val spaceXl: Dp = 40.dp,
    val margin: Dp = 16.dp,
    val gutter: Dp = 12.dp,
)

val LocalScoldSpacing = staticCompositionLocalOf { ScoldSpacing() }
