package com.scold.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp

@Composable
fun ComposePostScreen(
    onPostSubmitted: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var postContent by remember { mutableStateOf("") }
    val maxChars = 280

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0C))
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color(0xFF0E0E10))
                .border(1.dp, Color(0xFF2A2E39), RectangleShape)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFFEDEDF0)
                )
            }
            Column {
                Text(
                    text = "// COMPOSE",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF8F94A0)
                )
                Text(
                    text = "DISPATCH ENTRY",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFFEDEDF0)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Quota Tax Alert
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEE9800))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    tint = Color(0xFF5B3800),
                    modifier = Modifier.size(20.dp)
                )
                Column {
                    Text(
                        text = "ATTENTION ALLOCATION WARNING",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF5B3800)
                    )
                    Text(
                        text = "Creating a post consumes 1.5 daily quota tokens. Proceed only if content contains strictly necessary telemetry.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF472A00)
                    )
                }
            }

            // Input Slot
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF16171B))
                    .border(1.dp, Color(0xFF2A2E39), RectangleShape)
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "POST CONTENT REGISTER",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF8F94A0)
                )

                BasicTextField(
                    value = postContent,
                    onValueChange = { if (it.length <= maxChars) postContent = it },
                    textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFFEDEDF0)),
                    cursorBrush = SolidColor(Color(0xFFF59E0B)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .background(Color(0xFF0E0E10))
                        .border(1.dp, Color(0xFF3F4452), RectangleShape)
                        .padding(10.dp),
                    decorationBox = { innerTextField ->
                        Box {
                            if (postContent.isEmpty()) {
                                Text(
                                    text = "Enter strictly necessary communication here. Mindless updates will be down-scored.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF5A6072)
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "REMAINING: ${maxChars - postContent.length} CHARS",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF8F94A0)
                    )
                    Text(
                        text = "ESTIMATED GUILT: 04/100",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFF59E0B)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Action
            Button(
                onClick = onPostSubmitted,
                enabled = postContent.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF5545),
                    contentColor = Color(0xFF5C0002),
                    disabledContainerColor = Color(0xFF20232B),
                    disabledContentColor = Color(0xFF8F94A0)
                )
            ) {
                Text(
                    text = "DISPATCH LOG ENTRY // DEDUCT QUOTA",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}
