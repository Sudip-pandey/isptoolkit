package com.pandey.isptoolkit.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pandey.isptoolkit.ui.theme.*

@Composable
fun StatusChip(statusText: String, modifier: Modifier = Modifier) {
    val bgColor = when (statusText.uppercase()) {
        "PASS", "EXCELLENT", "GOOD", "ONLINE" -> AccentGreen.copy(alpha = 0.2f)
        "WARNING", "FAIR", "MEDIUM" -> AccentAmber.copy(alpha = 0.2f)
        "FAIL", "POOR", "HIGH" -> AccentRed.copy(alpha = 0.2f)
        else -> SurfaceVariantDark
    }
    val textColor = when (statusText.uppercase()) {
        "PASS", "EXCELLENT", "GOOD", "ONLINE" -> AccentGreen
        "WARNING", "FAIR", "MEDIUM" -> AccentAmber
        "FAIL", "POOR", "HIGH" -> AccentRed
        else -> TextSecondary
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = statusText,
            style = MaterialTheme.typography.labelSmall,
            color = textColor
        )
    }
}