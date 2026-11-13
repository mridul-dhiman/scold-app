package com.scold.app.ai

import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GuiltCopyGenerator @Inject constructor() {

    private val nightCopies = listOf(
        "It's late. What void are you trying to fill right now?",
        "Your sleep cycle is decaying with every second here.",
        "Your eyes are burning and there is nothing new on this screen.",
        "Tomorrow's regret begins with this session."
    )

    private val dayCopies = listOf(
        "Why are you staring into this glass rectangle in broad daylight?",
        "Someone else is doing the work you are avoiding.",
        "Dopamine scavenger detected. Return to your actual responsibilities.",
        "Your future self is watching you waste this precise minute."
    )

    private val frequentOpenCopies = listOf(
        "You opened this multiple times today. Expecting a miracle?",
        "Nothing has changed since 3 minutes ago. Absolutely nothing.",
        "Compulsive reflex confirmed. Put the device down.",
        "Make it quick. The timer is already unforgiving."
    )

    fun getGuiltStatement(opensToday: Int): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when {
            opensToday >= 5 -> frequentOpenCopies.random()
            hour in 0..5 || hour >= 22 -> nightCopies.random()
            else -> dayCopies.random()
        }
    }
}
