package com.amarilisnails.app.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onClientsClick: () -> Unit,
    onAppointmentsClick: () -> Unit,
    onServicesClick: () -> Unit,
    onSummaryClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
            .padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "AmarilisNails",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "¡Hola, Amarilis!",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Resumen de hoy", style = MaterialTheme.typography.titleMedium
        )

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SummaryCard(
                title = "3", subtitle = "Citas hoy", modifier = Modifier.weight(1f)
            )

            SummaryCard(
                title = "120 €", subtitle = "Facturado hoy", modifier = Modifier.weight(1f)
            )
        }

        Text(
            text = "Resumen de este mes", style = MaterialTheme.typography.titleMedium
        )

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SummaryCard(
                title = "850 €", subtitle = "Facturado", modifier = Modifier.weight(1f)
            )

            SummaryCard(
                title = "770 €", subtitle = "Cobrado", modifier = Modifier.weight(1f)
            )

            SummaryCard(
                title = "80 €", subtitle = "Pendiente", modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        HomeMenuItem(
            title = "Clientas", onClick = onClientsClick
        )

        HomeMenuItem(
            title = "Citas", onClick = onAppointmentsClick
        )

        HomeMenuItem(
            title = "Servicios", onClick = onServicesClick
        )

        HomeMenuItem(
            title = "Resumen mensual", onClick = onSummaryClick
        )
    }

}

@Composable
private fun SummaryCard(
    title: String, subtitle: String, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(86.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
            )
        }
    }
}

@Composable
private fun HomeMenuItem(
    title: String, onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().height(56.dp).clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = ">",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}