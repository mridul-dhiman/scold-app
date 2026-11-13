package com.scold.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Monitoring
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.filled.ViewTimeline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavBar(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val items = listOf(
        Triple("feed", "// FEED", Icons.Default.ViewTimeline),
        Triple("post", "// POST", Icons.Default.AddBox),
        Triple("stats", "// STATS", Icons.Default.Monitoring),
        Triple("exit", "// EXIT", Icons.Default.PowerSettingsNew),
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color(0xFF0E0E10))
            .border(1.dp, Color(0xFF2A2E39), RectangleShape),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { (route, label, icon) ->
            val isSelected = currentRoute == route
            val itemBackground = if (isSelected) Color(0xFF20232B) else Color.Transparent
            val itemColor = if (isSelected) Color(0xFFFF5545) else Color(0xFF8F94A0)

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(itemBackground, RectangleShape)
                    .clickable { onNavigate(route) },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = itemColor
                    )
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        color = itemColor
                    )
                }
            }
        }
    }
}
