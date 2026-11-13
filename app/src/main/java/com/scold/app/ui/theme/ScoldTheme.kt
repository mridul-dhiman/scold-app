package com.scold.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

/**
 * Scold's "Hostile Architecture" theme wrapper.
 *
 * Provides Material3 colors, typography, shapes — plus extended
 * [ScoldExtendedColors] and [ScoldSpacing] via CompositionLocals.
 */
@Composable
fun ScoldTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) ScoldDarkColorScheme else ScoldLightColorScheme
    val extendedColors = if (darkTheme) DarkExtendedColors else LightExtendedColors

    CompositionLocalProvider(
        LocalScoldExtendedColors provides extendedColors,
        LocalScoldSpacing provides ScoldSpacing(),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = ScoldTypography,
            shapes = ScoldShapes,
            content = content,
        )
    }
}

/** Convenience accessors so callers don't touch CompositionLocals directly. */
object ScoldThemeAccessors {
    val extendedColors: ScoldExtendedColors
        @Composable get() = LocalScoldExtendedColors.current

    val spacing: ScoldSpacing
        @Composable get() = LocalScoldSpacing.current
}
