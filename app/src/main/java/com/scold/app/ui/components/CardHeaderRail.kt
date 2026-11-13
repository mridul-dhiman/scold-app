package com.scold.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CardHeaderRail(
    moduleId: String,
    label: String,
    statusLabel: String,
    chipStatus: ChipStatus = ChipStatus.CONTAINED,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(28.dp)
                .background(Color(0xFF16171B))
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "$moduleId // $label".uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF8F94A0),
            )
            TelemetryChip(label = statusLabel, status = chipStatus)
        }
        HorizontalDivider(thickness = 1.dp, color = Color(0xFF2A2E39))
    }
}
