package com.scold.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.graphics.RectangleShape

/**
 * Hostile Architecture shapes: every corner is 0px — pure rectangles.
 */
val ScoldShapes = Shapes(
    extraSmall = RectangleShape,
    small = RectangleShape,
    medium = RectangleShape,
    large = RectangleShape,
    extraLarge = RectangleShape,
)
