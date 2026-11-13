package com.scold.app.util

/**
 * Validates text entry to prevent clipboard pasting.
 * If the length delta is greater than 1, it's flagged as an automated or pasted input.
 */
object PasteBlocker {
    fun isPasteAttempt(previousText: String, newText: String): Boolean {
        val diff = newText.length - previousText.length
        return diff > 1
    }
}
