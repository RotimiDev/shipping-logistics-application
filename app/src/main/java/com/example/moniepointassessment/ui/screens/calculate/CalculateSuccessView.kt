package com.example.moniepointassessment.ui.screens.calculate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moniepointassessment.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MoveMateAnimatedPriceScreenPreview() {
    MaterialTheme {
        CalculateSuccessView()
    }
}

@Composable
fun CalculateSuccessView(
    startAmount: Int = 1071,
    endAmount: Int = 1460,
    onBackClick: () -> Unit = {}
) {

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.White,
            darkIcons = true
        )
        systemUiController.setNavigationBarColor(
            color = Color.White,
            darkIcons = false
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_move_mate_logo),
                contentDescription = "MoveMate logo",
                modifier = Modifier.height(44.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_shipment_box),
                contentDescription = "Shipment box",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Total Estimated Amount",
                color = Color(0xFF1A1A1A),
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 22.sp, fontWeight = FontWeight.W400)
            )

            Spacer(modifier = Modifier.height(6.dp))

            AnimatedPriceDisplay(
                startAmount = startAmount,
                endAmount = endAmount
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "This amount is estimated this will vary\nif you change your location or weight",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp, fontWeight = FontWeight.W400),
                textAlign = TextAlign.Center,
                color = Color.Gray,
                lineHeight = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp)),
                contentPadding = PaddingValues(horizontal = 24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7A29))
            ) {
                Text(
                    text = "Back to home",
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
