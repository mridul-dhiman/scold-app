package com.scold.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun RedactedMediaCard(
    username: String,
    trivialityIndex: Int = 89,
    stimIndex: Int = 89,
    onUnlockRequested: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF0E0E10))
            .border(1.dp, Color(0xFF2A2E39), RectangleShape)
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(Color(0xFF2A2E39), RectangleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = username.take(2).uppercase(),
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(0xFFEDEDF0)
                    )
                }

                Column {
                    Text(
                        text = "@$username",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color(0xFFEDEDF0)
                    )
                    Text(
                        text = "ATTACHMENT ENCRYPTED",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF8F94A0)
                    )
                }
            }

            TelemetryChip(label = "REDACTED", status = ChipStatus.BREACH)
        }

        // Redacted blurred bay
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color(0xFF16171B))
                .border(1.dp, Color(0xFF3F4452), RectangleShape)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color(0xFFFF5545), RectangleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = Color(0xFF5C0002),
                        modifier = Modifier.size(20.dp)
                    )
                }

                Text(
                    text = "[HIGH-DOPAMINE MEDIA REDACTED]",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFFFF5545),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Video contains non-essential stimuli. $trivialityIndex% triviality index. Prefrontal cortex cold-boot required.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFEDEDF0),
                    textAlign = TextAlign.Center
                )

                Button(
                    onClick = onUnlockRequested,
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2A2E39),
                        contentColor = Color(0xFFEDEDF0)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Psychology,
                        contentDescription = null,
                        tint = Color(0xFFF59E0B),
                        modifier = Modifier.size(16.dp).padding(end = 4.dp)
                    )
                    Text(
                        text = "[ ENGAGE MATH INTERLOCK ]",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }

        // Footer telemetry
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "MEDIA TYPE: MP4_DEPRECIATED",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF8F94A0)
            )
            Text(
                text = "STIM_INDEX: $stimIndex/100",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF8F94A0)
            )
        }
    }
}
