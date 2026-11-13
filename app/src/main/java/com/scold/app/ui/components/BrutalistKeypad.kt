package com.scold.app.ui.components

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp

@Composable
fun BrutalistKeypad(
    onDigit: (Char) -> Unit,
    onClear: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val view = LocalView.current
    val rows = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("CLR", "0", "DEL"),
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF0E0E10))
            .border(1.dp, Color(0xFF2A2E39), RectangleShape),
        verticalArrangement = Arrangement.spacedBy(1.dp),
    ) {
        rows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(1.dp),
            ) {
                row.forEach { key ->
                    val isAction = key == "CLR" || key == "DEL"
                    val textColor = when (key) {
                        "CLR" -> Color(0xFFFF3B30)
                        "DEL" -> Color(0xFFF59E0B)
                        else -> Color(0xFFEDEDF0)
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                            .background(Color(0xFF1C1E24), RectangleShape)
                            .clickable {
                                view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
                                when (key) {
                                    "CLR" -> onClear()
                                    "DEL" -> onDelete()
                                    else -> onDigit(key[0])
                                }
                            },
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = key,
                            style = if (isAction) MaterialTheme.typography.labelMedium else MaterialTheme.typography.headlineSmall,
                            color = textColor,
                        )
                    }
                }
            }
        }
    }
}
