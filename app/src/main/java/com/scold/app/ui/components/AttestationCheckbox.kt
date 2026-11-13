package com.scold.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun AttestationCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clickable { onCheckedChange(!checked) }
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Box(
            modifier = Modifier
                .padding(top = 2.dp, end = 8.dp)
                .size(16.dp)
                .border(1.dp, if (checked) Color(0xFFFF3B30) else Color(0xFF3F4452), RectangleShape)
                .background(if (checked) Color(0xFFFF3B30) else Color.Transparent, RectangleShape),
            contentAlignment = Alignment.Center,
        ) {
            if (checked) {
                Text(
                    text = "×",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF0A0A0C),
                )
            }
        }

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = if (checked) Color(0xFFEDEDF0) else Color(0xFF8F94A0),
        )
    }
}
