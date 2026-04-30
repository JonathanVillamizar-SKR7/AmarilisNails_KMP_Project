package com.amarilisnails.app.navigation

import androidx.compose.runtime.Composable
import com.amarilisnails.app.presentation.home.HomeScreen

@Composable
fun NavigationWrapper() {
    HomeScreen(
        onClientsClick = { },
        onAppointmentsClick = { },
        onServicesClick = { },
        onSummaryClick = { })
}