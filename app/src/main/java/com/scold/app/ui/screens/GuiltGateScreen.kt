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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.scold.app.ai.GuiltCopyGenerator
import com.scold.app.timer.SessionTimerService

@Composable
fun GuiltGateScreen(
    onAccepted: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val guiltGenerator = remember { GuiltCopyGenerator() }
    val guiltText = remember { guiltGenerator.getGuiltStatement(opensToday = 4) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0C))
            .verticalScroll(rememberScrollState()),
    ) {
        // Harsh Marquee Header Rail
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF93000A))
                .padding(horizontal = 16.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    tint = Color(0xFFFFDAD6),
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = "[PRE-SESSION DISCIPLINARY INTERCEPT]",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFFFDAD6),
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "SYS_REV: 04.99.2",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFFFFDAD6)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Guilt statement card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0E0E10))
                    .border(1.dp, Color(0xFF2A2E39), RectangleShape)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "CONTAINMENT VERDICT",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFFF3B30),
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = guiltText.uppercase(),
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFFEDEDF0),
                    lineHeight = MaterialTheme.typography.headlineLarge.lineHeight
                )

                Text(
                    text = "You are entering a high-friction terminal environment. Every second consumed here counts towards your non-negotiable daily quota. Once exhausted, terminal lockdown is strictly enforced.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF8F94A0)
                )
            }

            // Quota rules telemetry
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF16171B))
                    .border(1.dp, Color(0xFF20232B), RectangleShape)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "SESSION PROTOCOL RULES",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFF59E0B)
                )

                val rules = listOf(
                    "01 // Maximum window: 03 minutes (180.00s)",
                    "02 // UI color matrix degrades into grayscale at 60s remaining",
                    "03 // Full lockout overlay drops at 00s with no gesture exit",
                    "04 // Bypass requires verbatim typing penalty with zero clipboard access"
                )

                rules.forEach { rule ->
                    Text(
                        text = rule,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFEDEDF0)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Action
            Button(
                onClick = {
                    SessionTimerService.startSession(context, 180)
                    onAccepted()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF5545),
                    contentColor = Color(0xFF5C0002)
                )
            ) {
                Text(
                    text = "ACCEPT TERMS & PROCEED TO TERMINAL",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "DISCIPLINARY HARNESS ACTIVE // ZERO ADVERTISING TRACKING",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF5A6072),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
