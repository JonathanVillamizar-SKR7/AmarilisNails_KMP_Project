package com.amarilisnails.app.presentation.appointments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amarilisnails.app.domain.model.Appointment
import com.amarilisnails.app.domain.model.Client
import com.amarilisnails.app.presentation.clients.ClientsViewModel
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

@Composable
fun TodayAppointmentsScreen(
    appointmentsViewModel: AppointmentsViewModel,
    clientsViewModel: ClientsViewModel,
    onBackClick: () -> Unit,
    onNewAppointmentClick: () -> Unit
) {
    val allAppointments by appointmentsViewModel.appointments.collectAsState()
    val clients by clientsViewModel.clients.collectAsState()

    var selectedDate by remember { mutableStateOf(LocalDate(2026, 5, 14)) }

    val dayAppointments = allAppointments.filter { it.date == selectedDate }.sortedBy { it.time }

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
            .padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "< Volver",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                onBackClick()
            })

        Text(
            text = "Agenda del día",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = {
                    selectedDate = selectedDate.minus(DatePeriod(days = 1))
                }) {
                Text("<")
            }

            Text(
                text = formatDate(selectedDate),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleMedium
            )

            OutlinedButton(
                onClick = {
                    selectedDate = selectedDate.plus(DatePeriod(days = 1))
                }) {
                Text(">")
            }
        }

        if (dayAppointments.isEmpty()) {
            EmptyAppointmentsCard()
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.weight(1f)
            ) {
                items(dayAppointments) { appointment ->
                    val client = clients.find { it.id == appointment.clientId }

                    TodayAppointmentItem(
                        appointment = appointment, client = client
                    )
                }
            }
        }

        Button(
            onClick = onNewAppointmentClick,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("+ Nueva cita")
        }
    }
}

@Composable
private fun TodayAppointmentItem(
    appointment: Appointment, client: Client?
) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top
    ) {
        Text(
            text = formatTime(appointment.time),
            modifier = Modifier.width(58.dp),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(
                modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = client?.let { "${it.name} ${it.surname}" } ?: "Clienta desconocida",
                    style = MaterialTheme.typography.titleMedium)

                if (appointment.notes.isNotBlank()) {
                    Text(
                        text = appointment.notes, style = MaterialTheme.typography.bodyMedium
                    )
                }

                Text(
                    text = if (appointment.paymentStatus.name == "PAID") "Pagado" else "Pendiente",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Total: ${formatPrice(appointment.total)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

private fun formatPrice(price: Double): String {
    val rounded = (price * 100).toInt() / 100.0
    return "$rounded €".replace(".", ",")
}

@Composable
private fun EmptyAppointmentsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().padding(20.dp), contentAlignment = Alignment.Center
        ) {
            Text("No hay citas para este día.")
        }
    }
}

private fun formatDate(date: LocalDate): String {
    return "${date.dayOfMonth.toString().padStart(2, '0')}/" + "${
        date.monthNumber.toString().padStart(2, '0')
    }/" + "${date.year}"
}

private fun formatTime(time: kotlinx.datetime.LocalTime): String {
    return "${time.hour.toString().padStart(2, '0')}:" + time.minute.toString().padStart(2, '0')
}