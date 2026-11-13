package com.scold.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
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
import androidx.compose.ui.unit.dp
import com.scold.app.data.repository.Post

@Composable
fun FeedCard(
    post: Post,
    onAcknowledge: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val initials = post.username.take(2).uppercase()

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
                        text = initials,
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(0xFFEDEDF0)
                    )
                }

                Column {
                    Text(
                        text = "@${post.username}",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color(0xFFEDEDF0)
                    )
                    Text(
                        text = "SEC_LOG #${post.id} • PEER ENTRY",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF8F94A0)
                    )
                }
            }

            TelemetryChip(
                label = "GUILT: ${post.guiltScore}/100",
                status = if (post.guiltScore > 10) ChipStatus.RESTRICTED else ChipStatus.CONTAINED
            )
        }

        // Post body
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF16171B))
                .border(1.dp, Color(0xFF20232B), RectangleShape)
                .padding(12.dp)
        ) {
            Text(
                text = post.content,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFEDEDF0)
            )
        }

        // Action footer
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = null,
                    tint = Color(0xFFFF5545),
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "READ RECEIPT COMPULSORY",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF8F94A0)
                )
            }

            Button(
                onClick = { onAcknowledge(post.id) },
                enabled = !post.isAcknowledged,
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF5545),
                    contentColor = Color(0xFF5C0002),
                    disabledContainerColor = Color(0xFF20232B),
                    disabledContentColor = Color(0xFF8F94A0)
                )
            ) {
                Text(
                    text = if (post.isAcknowledged) "[ ACKNOWLEDGED ]" else "[ ACKNOWLEDGE ]",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}
