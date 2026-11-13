package com.scold.app.ui.components

import android.view.HapticFeedbackConstants
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.scold.app.util.PasteBlocker
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun PenaltyTextInput(
    targetPhrase: String,
    onMatch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val view = LocalView.current
    val scope = rememberCoroutineScope()
    var typedText by remember { mutableStateOf("") }
    var mismatchCount by remember { mutableIntStateOf(0) }
    var matchCount by remember { mutableIntStateOf(0) }
    val shakeOffset = remember { Animatable(0f) }

    fun triggerShake() {
        view.performHapticFeedback(HapticFeedbackConstants.REJECT)
        scope.launch {
            shakeOffset.snapTo(0f)
            for (i in 0..2) {
                shakeOffset.animateTo(12f, tween(30))
                shakeOffset.animateTo(-12f, tween(30))
            }
            shakeOffset.animateTo(0f, tween(30))
        }
    }

    Column(
        modifier = modifier
            .offset { IntOffset(shakeOffset.value.roundToInt(), 0) }
            .fillMaxWidth()
            .background(Color(0xFF121316))
            .border(1.dp, Color(0xFF2A2E39), RectangleShape)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        // Target phrase slate
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1C1E24))
                .border(1.dp, Color(0xFF3F4452), RectangleShape)
                .padding(10.dp)
        ) {
            Text(
                text = "EXACT TARGET PHRASE:",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF8F94A0),
            )
            Text(
                text = targetPhrase,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFFF3B30),
                modifier = Modifier.padding(top = 4.dp),
            )
        }

        // Telemetry header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "INPUT_REGISTER:",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF8F94A0),
            )
            Text(
                text = "MISMATCH: $mismatchCount",
                style = MaterialTheme.typography.labelSmall,
                color = if (mismatchCount > 0) Color(0xFFFF3B30) else Color(0xFF8F94A0),
            )
        }

        // Actual input slot
        BasicTextField(
            value = typedText,
            onValueChange = { newText ->
                if (PasteBlocker.isPasteAttempt(typedText, newText)) {
                    // Clipboard paste blocked!
                    triggerShake()
                    return@BasicTextField
                }

                typedText = newText

                // Calculate accuracy
                var mismatches = 0
                var matches = 0
                for (i in newText.indices) {
                    if (i < targetPhrase.length && newText[i] == targetPhrase[i]) {
                        matches++
                    } else {
                        mismatches++
                    }
                }
                matchCount = matches
                mismatchCount = mismatches

                if (mismatches > 0) {
                    view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
                }

                if (newText == targetPhrase) {
                    view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                    onMatch()
                }
            },
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFFEDEDF0)),
            cursorBrush = SolidColor(Color(0xFFF59E0B)),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0E0E10))
                .border(1.dp, if (mismatchCount > 0) Color(0xFFFF3B30) else Color(0xFF3F4452), RectangleShape)
                .padding(10.dp),
            decorationBox = { innerTextField ->
                Box {
                    if (typedText.isEmpty()) {
                        Text(
                            text = "Type exact phrase to unlock bypass...",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF5A6072),
                        )
                    }
                    innerTextField()
                }
            }
        )

        // Telemetry footer
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "MATCH: $matchCount / ${targetPhrase.length} CHARS",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF8F94A0),
            )
            val status = when {
                typedText == targetPhrase -> "VERIFIED" to Color(0xFFF59E0B)
                mismatchCount > 0 -> "SYNTAX CORRUPTED" to Color(0xFFFF3B30)
                else -> "INCOMPLETE" to Color(0xFF8F94A0)
            }
            Text(
                text = status.first,
                style = MaterialTheme.typography.labelSmall,
                color = status.second,
            )
        }
    }
}
