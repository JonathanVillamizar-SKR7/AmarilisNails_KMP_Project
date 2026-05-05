package com.amarilisnails.app.presentation.appointments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

@Composable
fun AppointmentFormScreen(
    clientId: String, viewModel: AppointmentsViewModel, onBackClick: () -> Unit
) {
    var date by remember { mutableStateOf("") }
    var hour by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
            .padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        Text(
            text = "Nueva cita",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Fecha (ej: 30/04/2026)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = hour,
            onValueChange = { hour = it },
            label = { Text("Hora (ej: 16:30)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notas") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (date.isNotBlank() && hour.isNotBlank()) {
                    try {
                        val parsedDate = LocalDate.parse(date)
                        val parsedTime = LocalTime.parse(hour)

                        viewModel.addAppointment(
                            clientId = clientId, date = parsedDate, time = parsedTime, notes = notes
                        )
                        onBackClick()
                    } catch (e: Exception) {
                        println("Error al parsear la fecha o la hora: $e")
                    }
                }
            }, modifier = Modifier.fillMaxWidth().height(56.dp), shape = RoundedCornerShape(16.dp)
        ) {
            Text("Guardar cita")
        }

        OutlinedButton(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Cancelar")
        }
    }
}