package com.myapp.buckwheat.ui.main.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AmountDisplay(
    amount: String,
    currencySymbol: String,
    modifier: Modifier = Modifier
) {
    val displayText = "$currencySymbol $amount"

    // Auto-sizing: shrink font as text gets longer
    val fontSize = when {
        displayText.length > 15 -> 28.sp
        displayText.length > 12 -> 32.sp
        displayText.length > 9 -> 36.sp
        displayText.length > 6 -> 42.sp
        else -> 48.sp
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),
        contentAlignment = Alignment.Center
    ) {
        AnimatedContent(
            targetState = displayText,
            transitionSpec = {
                (slideInVertically { height -> height } + fadeIn()) togetherWith
                        (slideOutVertically { height -> -height } + fadeOut())
            },
            label = "amountAnimation"
        ) { text ->
            Text(
                text = text,
                style = MaterialTheme.typography.displayMedium.copy(fontSize = fontSize),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
