package com.scold.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = com.scold.app.R.array.com_google_android_gms_fonts_certs,
)

private val SpaceMonoFont = GoogleFont("Space Mono")
private val JetBrainsMonoFont = GoogleFont("JetBrains Mono")

val SpaceMonoFamily = FontFamily(
    Font(googleFont = SpaceMonoFont, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = SpaceMonoFont, fontProvider = provider, weight = FontWeight.Bold),
)

val JetBrainsMonoFamily = FontFamily(
    Font(googleFont = JetBrainsMonoFont, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = JetBrainsMonoFont, fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = JetBrainsMonoFont, fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = JetBrainsMonoFont, fontProvider = provider, weight = FontWeight.Bold),
)

// Tabular figures via OpenType font feature settings
private const val TabularFigures = "tnum"

val ScoldTypography = Typography(
    // ── Display / Headlines: Space Mono ────────────────────────────────────
    displayLarge = TextStyle(
        fontFamily = SpaceMonoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        lineHeight = 44.sp,
        letterSpacing = (-0.5).sp,
        fontFeatureSettings = TabularFigures,
    ),
    displayMedium = TextStyle(
        fontFamily = SpaceMonoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.25).sp,
        fontFeatureSettings = TabularFigures,
    ),
    displaySmall = TextStyle(
        fontFamily = SpaceMonoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp,
        fontFeatureSettings = TabularFigures,
    ),
    headlineLarge = TextStyle(
        fontFamily = SpaceMonoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.25).sp,
        fontFeatureSettings = TabularFigures,
    ),
    headlineMedium = TextStyle(
        fontFamily = SpaceMonoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
        fontFeatureSettings = TabularFigures,
    ),
    headlineSmall = TextStyle(
        fontFamily = SpaceMonoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp,
        fontFeatureSettings = TabularFigures,
    ),

    // ── Body: JetBrains Mono ──────────────────────────────────────────────
    bodyLarge = TextStyle(
        fontFamily = JetBrainsMonoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp,
        fontFeatureSettings = TabularFigures,
    ),
    bodyMedium = TextStyle(
        fontFamily = JetBrainsMonoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
        fontFeatureSettings = TabularFigures,
    ),
    bodySmall = TextStyle(
        fontFamily = JetBrainsMonoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp,
        fontFeatureSettings = TabularFigures,
    ),

    // ── Labels: JetBrains Mono, UPPERCASE, wide letter-spacing ────────────
    labelLarge = TextStyle(
        fontFamily = JetBrainsMonoFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.72.sp,  // ~0.06em at 12sp
        fontFeatureSettings = TabularFigures,
    ),
    labelMedium = TextStyle(
        fontFamily = JetBrainsMonoFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.8.sp,   // ~0.08em at 10sp
        fontFeatureSettings = TabularFigures,
    ),
    labelSmall = TextStyle(
        fontFamily = JetBrainsMonoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 9.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.9.sp,   // ~0.1em at 9sp
        fontFeatureSettings = TabularFigures,
    ),

    // ── Title slots (fallback — using JetBrains Mono SemiBold) ────────────
    titleLarge = TextStyle(
        fontFamily = JetBrainsMonoFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
        fontFeatureSettings = TabularFigures,
    ),
    titleMedium = TextStyle(
        fontFamily = JetBrainsMonoFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp,
        fontFeatureSettings = TabularFigures,
    ),
    titleSmall = TextStyle(
        fontFamily = JetBrainsMonoFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
        fontFeatureSettings = TabularFigures,
    ),
)
