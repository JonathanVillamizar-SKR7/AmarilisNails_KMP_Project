package com.amarilisnails.app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AmarilisScaffold(
    currentRoute: String,
    onHomeClick: () -> Unit,
    onAppointmentsClick: () -> Unit,
    onNewAppointmentClick: () -> Unit,
    onClientsClick: () -> Unit,
    onMoreClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(bottom = 90.dp)
        ) {
            ResponsiveContent {
                content()
            }
        }

        Box(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
        ) {
            AmarilisBottomBar(
                currentRoute = currentRoute,
                onHomeClick = onHomeClick,
                onAppointmentsClick = onAppointmentsClick,
                onNewAppointmentClick = onNewAppointmentClick,
                onClientsClick = onClientsClick,
                onMoreClick = onMoreClick
            )
        }
    }
}