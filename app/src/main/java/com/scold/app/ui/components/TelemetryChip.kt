package com.scold.app.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

enum class ChipStatus { BREACH, RESTRICTED, CONTAINED }

@Composable
fun TelemetryChip(
    label: String,
    status: ChipStatus = ChipStatus.CONTAINED,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier,
) {
    val (borderColor, textColor) = when (status) {
        ChipStatus.BREACH -> Color(0xFFFF3B30) to Color(0xFFFF3B30)
        ChipStatus.RESTRICTED -> Color(0xFFF59E0B) to Color(0xFFF59E0B)
        ChipStatus.CONTAINED -> Color(0xFF3F4452) to Color(0xFF8F94A0)
    }

    Row(
        modifier = modifier
            .border(width = 1.dp, color = borderColor, shape = RectangleShape)
            .padding(horizontal = 6.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier
                    .size(12.dp)
                    .padding(end = 3.dp)
            )
        }
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = textColor,
        )
    }
}
