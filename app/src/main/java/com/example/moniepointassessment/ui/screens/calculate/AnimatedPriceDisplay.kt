package com.example.moniepointassessment.ui.screens.calculate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun AnimatedPriceDisplay(
    startAmount: Int,
    endAmount: Int,
    modifier: Modifier = Modifier,
    currency: String = "USD",
    prefix: String = "$",
    step: Int = 5,
    intervalMs: Long = 8L
) {
    var currentAmount by remember { mutableIntStateOf(startAmount) }

    LaunchedEffect(Unit) {
        delay(300)
        while (currentAmount < endAmount) {
            delay(intervalMs)
            currentAmount = (currentAmount + step).coerceAtMost(endAmount)
        }
    }

    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = "$prefix$currentAmount",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.W500),
            color = Color(0xFF30C18C)
        )
        Text(
            text = currency,
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 22.sp, fontWeight = FontWeight.W400),
            color = Color(0xFF30C18C),
            modifier = Modifier
                .padding(start = 2.dp, bottom = 3.dp)
        )
    }
}
