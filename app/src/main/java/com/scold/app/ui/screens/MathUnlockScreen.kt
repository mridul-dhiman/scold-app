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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Shield
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
import androidx.compose.ui.unit.dp
import com.scold.app.ui.components.AttestationCheckbox
import com.scold.app.ui.components.BrutalistKeypad
import com.scold.app.ui.components.ChipStatus
import com.scold.app.ui.components.TelemetryChip

@Composable
fun MathUnlockScreen(
    onResist: () -> Unit,
    onUnlocked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var enteredPin by remember { mutableStateOf("") }
    var isAttested by remember { mutableStateOf(false) }
    val targetAnswer = "87" // (17 * 8) - 49 = 136 - 49 = 87
    val isCorrect = enteredPin == targetAnswer

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0C))
            .verticalScroll(rememberScrollState()),
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
            IconButton(onClick = onResist) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFFEDEDF0)
                )
            }
            Column {
                Text(
                    text = "// SUBROUTINE",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF8F94A0)
                )
                Text(
                    text = "INFRACTION DETAILS",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFFEDEDF0)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Tactical Overlay Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0E0E10))
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "GATE_INTERLOCK // #742-ALPHA",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFFF3B30)
                )
                TelemetryChip(label = "ACTIVE DEFENSE", status = ChipStatus.BREACH)
            }

            // Threat Warning Banner
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
                        text = "THREAT_VECTOR: HIGH-DOPAMINE LOOP DETECTED",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF5B3800)
                    )
                    Text(
                        text = "Autopilot scroll vector intercepted. Prefrontal cortex cold-boot required to unlock sensory stimulus.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF472A00)
                    )
                }
            }

            // Quarantined Media Preview
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF16171B))
                    .border(1.dp, Color(0xFF20232B), RectangleShape)
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "// QUARANTINED_STREAM",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFFF5545)
                    )
                    TelemetryChip(label = "TRIVIALITY: 94%", status = ChipStatus.BREACH)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .background(Color(0xFF0E0E10))
                        .border(1.dp, Color(0xFF2A2E39), RectangleShape)
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = Color(0xFFFF5545),
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "STIMULUS: \"Hydraulic Press Soap Cut Loop\"",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFEDEDF0)
                        )
                        Text(
                            text = "[DURATION: 00:38s // REWARD: JUNK METRICS]",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF8F94A0)
                        )
                    }
                }
            }

            // Logic Barrier Module
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF16171B))
                    .border(1.dp, Color(0xFF20232B), RectangleShape)
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "MODULE_07 // LOGIC_BARRIER",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF8F94A0)
                    )
                    TelemetryChip(label = "EVALUATION PENDING", status = ChipStatus.RESTRICTED)
                }

                // Formula Slate
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF0E0E10))
                        .border(1.dp, Color(0xFF2A2E39), RectangleShape)
                        .padding(14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "CHALLENGE_FORMULA:",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF8F94A0)
                    )
                    Text(
                        text = "(17 × 8) - 49 = ?",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color(0xFFFF5545)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "INPUT:",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF8F94A0)
                        )
                        Box(
                            modifier = Modifier
                                .size(width = 100.dp, height = 36.dp)
                                .background(Color(0xFF16171B))
                                .border(1.dp, Color(0xFF3F4452), RectangleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = enteredPin,
                                style = MaterialTheme.typography.headlineSmall,
                                color = Color(0xFFF59E0B)
                            )
                        }
                    }
                }

                // Keypad
                BrutalistKeypad(
                    onDigit = { digit ->
                        if (enteredPin.length < 4) enteredPin += digit
                    },
                    onClear = { enteredPin = "" },
                    onDelete = { if (enteredPin.isNotEmpty()) enteredPin = enteredPin.dropLast(1) }
                )

                // Attestation Checkbox
                AttestationCheckbox(
                    checked = isAttested,
                    onCheckedChange = { isAttested = it },
                    label = "[X] I consciously acknowledge this algorithmic media has zero real-world value and will deplete my neurochemical reserve."
                )

                // Actions
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = onResist,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF5545),
                            contentColor = Color(0xFF5C0002)
                        )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.Shield, contentDescription = null, modifier = Modifier.size(18.dp))
                            Text(text = "RESIST URGE // KEEP BLURRED", style = MaterialTheme.typography.headlineSmall)
                        }
                    }

                    Button(
                        onClick = onUnlocked,
                        enabled = isCorrect && isAttested,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFEE9800),
                            contentColor = Color(0xFF5B3800),
                            disabledContainerColor = Color(0xFF20232B),
                            disabledContentColor = Color(0xFF8F94A0)
                        )
                    ) {
                        Text(
                            text = if (isCorrect && isAttested) "VERIFY ANSWER & UNLOCK MEDIA" else "[VERIFICATION LOCKED]",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
