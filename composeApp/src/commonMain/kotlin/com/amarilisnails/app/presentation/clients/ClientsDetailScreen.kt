package com.amarilisnails.app.presentation.clients

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amarilisnails.app.domain.model.Client

@Composable
fun ClientDetailScreen(
    client: Client?, onBackClick: () -> Unit, onNewAppointmentClick: () -> Unit
) {
    if (client == null) {
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp)
        ) {
            Text("Clienta no encontrada")
            Button(onClick = onBackClick) {
                Text("Volver")
            }
        }
        return
    }

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
            .padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "< Volver",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 4.dp).clickable { onBackClick() })

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(78.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.18f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = client.name.firstOrNull()?.uppercase() ?: "?",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "${client.name} ${client.surname}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = client.phone, style = MaterialTheme.typography.bodyMedium
                )

                if (client.reference.isNotBlank()) {
                    Text(
                        text = "Referencia: ${client.reference}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Sellos", style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "☆ ☆ ☆ ☆ ☆ ☆   0/6",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Le faltan 6 sellos para su descuento.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Text(
            text = "Historial de citas", style = MaterialTheme.typography.titleMedium
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Text(
                text = "Todavía no hay citas registradas.",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onNewAppointmentClick,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("+ Nueva cita")
        }
    }
}