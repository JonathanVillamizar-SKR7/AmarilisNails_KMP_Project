package com.amarilisnails.app.presentation.appointments.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.*

@Composable
fun AmarilisDatePickerDialog(
    selectedDate: LocalDate, onDateSelected: (LocalDate) -> Unit, onDismiss: () -> Unit
) {
    var visibleMonth by remember {
        mutableStateOf(LocalDate(selectedDate.year, selectedDate.monthNumber, 1))
    }

    val daysInMonth = visibleMonth.daysInMonth()
    val firstDayOffset = visibleMonth.dayOfWeek.isoDayNumber - 1

    AlertDialog(
        onDismissRequest = onDismiss, title = {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "<", modifier = Modifier.clickable {
                    visibleMonth = visibleMonth.minusMonth()
                }, color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "${
                visibleMonth.month.name.lowercase().replaceFirstChar { it.uppercase() }
            } ${visibleMonth.year}",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary)

            Text(
                text = ">", modifier = Modifier.clickable {
                    visibleMonth = visibleMonth.plusMonth()
                }, color = MaterialTheme.colorScheme.primary
            )
        }
    }, text = {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                listOf("L", "M", "X", "J", "V", "S", "D").forEach {
                    Text(
                        text = it,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier.height(260.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(firstDayOffset) {
                    Box(modifier = Modifier.size(36.dp))
                }

                items((1..daysInMonth).toList()) { day ->
                    val date = LocalDate(
                        year = visibleMonth.year,
                        monthNumber = visibleMonth.monthNumber,
                        dayOfMonth = day
                    )

                    val isSelected = date == selectedDate

                    Surface(
                        modifier = Modifier.size(36.dp).clickable {
                            onDateSelected(date)
                            onDismiss()
                        }, shape = CircleShape, color = if (isSelected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.surface
                        }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = day.toString(), color = if (isSelected) {
                                    MaterialTheme.colorScheme.onPrimary
                                } else {
                                    MaterialTheme.colorScheme.onSurface
                                }
                            )
                        }
                    }
                }
            }
        }
    }, confirmButton = {}, dismissButton = {
        TextButton(onClick = onDismiss) {
            Text("Cancelar")
        }
    }, shape = RoundedCornerShape(24.dp)
    )
}

@Composable
fun AmarilisTimePickerDialog(
    selectedTime: LocalTime, onTimeSelected: (LocalTime) -> Unit, onDismiss: () -> Unit
) {
    var hour by remember { mutableStateOf(selectedTime.hour) }
    var minute by remember { mutableStateOf(selectedTime.minute) }

    AlertDialog(
        onDismissRequest = onDismiss, title = {
        Text(
            text = "Seleccionar hora", color = MaterialTheme.colorScheme.primary
        )
    }, text = {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberSelector(
                value = hour, range = 0..23, onValueChange = { hour = it })

            Text(
                text = ":",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            NumberSelector(
                value = minute, range = listOf(0, 15, 30, 45), onValueChange = { minute = it })
        }
    }, confirmButton = {
        TextButton(
            onClick = {
                onTimeSelected(LocalTime(hour, minute))
                onDismiss()
            }) {
            Text("Aceptar")
        }
    }, dismissButton = {
        TextButton(onClick = onDismiss) {
            Text("Cancelar")
        }
    }, shape = RoundedCornerShape(24.dp)
    )
}

@Composable
private fun NumberSelector(
    value: Int, range: Iterable<Int>, onValueChange: (Int) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TextButton(onClick = {
            val list = range.toList()
            val index = list.indexOf(value)
            onValueChange(list[(index + 1) % list.size])
        }) {
            Text("+")
        }

        Text(
            text = value.toString().padStart(2, '0'), style = MaterialTheme.typography.headlineLarge
        )

        TextButton(onClick = {
            val list = range.toList()
            val index = list.indexOf(value)
            onValueChange(list[(index - 1 + list.size) % list.size])
        }) {
            Text("-")
        }
    }
}

private fun LocalDate.daysInMonth(): Int {
    val nextMonth = if (monthNumber == 12) {
        LocalDate(year + 1, 1, 1)
    } else {
        LocalDate(year, monthNumber + 1, 1)
    }

    return nextMonth.minus(DatePeriod(days = 1)).dayOfMonth
}

private fun LocalDate.plusMonth(): LocalDate {
    return if (monthNumber == 12) {
        LocalDate(year + 1, 1, 1)
    } else {
        LocalDate(year, monthNumber + 1, 1)
    }
}

private fun LocalDate.minusMonth(): LocalDate {
    return if (monthNumber == 1) {
        LocalDate(year - 1, 12, 1)
    } else {
        LocalDate(year, monthNumber - 1, 1)
    }
}