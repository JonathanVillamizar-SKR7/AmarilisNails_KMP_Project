package com.amarilisnails.app.presentation.appointments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amarilisnails.app.presentation.appointments.components.AmarilisDatePickerDialog
import com.amarilisnails.app.presentation.appointments.components.AmarilisTimePickerDialog
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

@Composable
fun AppointmentFormScreen(
    clientId: String, viewModel: AppointmentsViewModel, onBackClick: () -> Unit
) {
    val today = remember {
        LocalDate(2026, 5, 5)
    }

    var selectedDate by remember { mutableStateOf(today) }
    var selectedTime by remember { mutableStateOf(LocalTime(10, 0)) }
    var notes by remember { mutableStateOf("") }

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

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
            value = formatDate(selectedDate),
            onValueChange = {},
            readOnly = true,
            label = { Text("Fecha") },
            modifier = Modifier.fillMaxWidth().clickable { showDatePicker = true },
            enabled = true
        )

        OutlinedTextField(
            value = formatTime(selectedTime),
            onValueChange = {},
            readOnly = true,
            label = { Text("Hora") },
            modifier = Modifier.fillMaxWidth().clickable { showTimePicker = true },
            enabled = true
        )

        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notas") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                viewModel.addAppointment(
                    clientId = clientId, date = selectedDate, time = selectedTime, notes = notes
                )
                onBackClick()
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

    if (showDatePicker) {
        AmarilisDatePickerDialog(
            selectedDate = selectedDate,
            onDateSelected = { selectedDate = it },
            onDismiss = { showDatePicker = false })
    }

    if (showTimePicker) {
        AmarilisTimePickerDialog(
            selectedTime = selectedTime,
            onTimeSelected = { selectedTime = it },
            onDismiss = { showTimePicker = false })
    }
}

private fun formatDate(date: LocalDate): String {
    return "${date.dayOfMonth.toString().padStart(2, '0')}/" + "${
        date.monthNumber.toString().padStart(2, '0')
    }/" + "${date.year}"
}

private fun formatTime(time: LocalTime): String {
    return "${time.hour.toString().padStart(2, '0')}:" + time.minute.toString().padStart(2, '0')
}