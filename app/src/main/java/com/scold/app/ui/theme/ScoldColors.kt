package com.scold.app.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// ── Dark palette ──────────────────────────────────────────────────────────────
private val DarkBackground = Color(0xFF0A0A0C)
private val DarkSurface = Color(0xFF121316)
private val DarkSurfaceVariant = Color(0xFF1C1E24)
private val DarkOnBackground = Color(0xFFEDEDF0)
private val DarkOnSurface = Color(0xFFEDEDF0)
private val DarkCrimson = Color(0xFFFF3B30)
private val DarkAmber = Color(0xFFF59E0B)
private val DarkZinc = Color(0xFF8F94A0)
private val DarkBorder = Color(0xFF2A2E39)
private val DarkBorderStrong = Color(0xFF3F4452)

// ── Light palette ─────────────────────────────────────────────────────────────
private val LightBackground = Color(0xFFF8F9FA)
private val LightSurface = Color(0xFFF1F3F5)
private val LightSurfaceVariant = Color(0xFFE9ECEF)
private val LightOnBackground = Color(0xFF111318)
private val LightOnSurface = Color(0xFF111318)
private val LightRed = Color(0xFFD32F2F)
private val LightOrange = Color(0xFFE65100)
private val LightZinc = Color(0xFF5A6072)
private val LightBorder = Color(0xFFD0D5DD)
private val LightBorderStrong = Color(0xFF111318)

val ScoldDarkColorScheme: ColorScheme = darkColorScheme(
    primary = DarkCrimson,
    onPrimary = Color.White,
    primaryContainer = DarkCrimson.copy(alpha = 0.15f),
    onPrimaryContainer = DarkCrimson,
    secondary = DarkAmber,
    onSecondary = Color.Black,
    secondaryContainer = DarkAmber.copy(alpha = 0.15f),
    onSecondaryContainer = DarkAmber,
    tertiary = DarkZinc,
    onTertiary = Color.White,
    background = DarkBackground,
    onBackground = DarkOnBackground,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkZinc,
    outline = DarkBorder,
    outlineVariant = DarkBorderStrong,
    error = DarkCrimson,
    onError = Color.White,
    errorContainer = DarkCrimson.copy(alpha = 0.15f),
    onErrorContainer = DarkCrimson,
)

val ScoldLightColorScheme: ColorScheme = lightColorScheme(
    primary = LightRed,
    onPrimary = Color.White,
    primaryContainer = LightRed.copy(alpha = 0.10f),
    onPrimaryContainer = LightRed,
    secondary = LightOrange,
    onSecondary = Color.White,
    secondaryContainer = LightOrange.copy(alpha = 0.10f),
    onSecondaryContainer = LightOrange,
    tertiary = LightZinc,
    onTertiary = Color.White,
    background = LightBackground,
    onBackground = LightOnBackground,
    surface = LightSurface,
    onSurface = LightOnSurface,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = LightZinc,
    outline = LightBorder,
    outlineVariant = LightBorderStrong,
    error = LightRed,
    onError = Color.White,
    errorContainer = LightRed.copy(alpha = 0.10f),
    onErrorContainer = LightRed,
)

// ── Extended colors not covered by Material3 ColorScheme ──────────────────────
@Immutable
data class ScoldExtendedColors(
    val crimson: Color,
    val amber: Color,
    val zinc: Color,
    val surfaceTier1: Color,
    val surfaceTier2: Color,
    val border: Color,
    val borderStrong: Color,
)

val DarkExtendedColors = ScoldExtendedColors(
    crimson = DarkCrimson,
    amber = DarkAmber,
    zinc = DarkZinc,
    surfaceTier1 = DarkSurface,
    surfaceTier2 = DarkSurfaceVariant,
    border = DarkBorder,
    borderStrong = DarkBorderStrong,
)

val LightExtendedColors = ScoldExtendedColors(
    crimson = LightRed,
    amber = LightOrange,
    zinc = LightZinc,
    surfaceTier1 = LightSurface,
    surfaceTier2 = LightSurfaceVariant,
    border = LightBorder,
    borderStrong = LightBorderStrong,
)

val LocalScoldExtendedColors = staticCompositionLocalOf { DarkExtendedColors }
