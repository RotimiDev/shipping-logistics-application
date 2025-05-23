package com.example.moniepointassessment.ui.screens.shipment

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun ShipmentScreen(onBackPressed: () -> Unit = {}, selectedMenuItem: Int = 0) {
    Column {
        MenuItemView(onBackPressed)
        ContentView(selectedMenuItem = selectedMenuItem)
    }
}
