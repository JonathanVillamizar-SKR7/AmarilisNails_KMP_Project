package com.amarilisnails.app.presentation.appointments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import com.amarilisnails.app.domain.model.AppointmentService
import com.amarilisnails.app.domain.model.PaymentMethod
import com.amarilisnails.app.domain.model.PaymentStatus
import com.amarilisnails.app.domain.model.Service
import com.amarilisnails.app.presentation.appointments.components.AmarilisDatePickerDialog
import com.amarilisnails.app.presentation.appointments.components.AmarilisTimePickerDialog
import com.amarilisnails.app.presentation.services.ServicesViewModel
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

@Composable
fun AppointmentFormScreen(
    clientId: String,
    appointmentsViewModel: AppointmentsViewModel,
    servicesViewModel: ServicesViewModel,
    onBackClick: () -> Unit
) {
    val today = remember { LocalDate(2026, 5, 5) }
    val services by servicesViewModel.services.collectAsState()

    var selectedDate by remember { mutableStateOf(today) }
    var selectedTime by remember { mutableStateOf(LocalTime(10, 0)) }
    var notes by remember { mutableStateOf("") }

    var paymentStatus by remember { mutableStateOf(PaymentStatus.PENDING) }
    var paymentMethod by remember { mutableStateOf<PaymentMethod?>(null) }

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    var selectedServices by remember {
        mutableStateOf<List<AppointmentService>>(emptyList())
    }

    val subtotal = selectedServices.sumOf { it.price * it.quantity }
    val discountPercentage = 0
    val discountAmount = subtotal * discountPercentage / 100
    val total = subtotal - discountAmount

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(
            start = 20.dp, end = 20.dp, top = 20.dp, bottom = 120.dp
        ),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Text(
                text = "< Volver",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { onBackClick() })
        }

        item {
            Text(
                text = "Nueva cita",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Box(modifier = Modifier.weight(1f)) {
                    OutlinedTextField(
                        value = formatDate(selectedDate),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Fecha") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Box(
                        modifier = Modifier.matchParentSize().clickable { showDatePicker = true })
                }

                Box(modifier = Modifier.weight(1f)) {
                    OutlinedTextField(
                        value = formatTime(selectedTime),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Hora") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Box(
                        modifier = Modifier.matchParentSize().clickable { showTimePicker = true })
                }
            }
        }

        item {
            Text(
                text = "Servicios", style = MaterialTheme.typography.titleMedium
            )
        }

        items(services.filter { it.isActive }) { service ->
            ServiceSelectableItem(
                service = service,
                selectedServices = selectedServices,
                onSelectedServicesChange = { selectedServices = it })
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SummaryRow("Subtotal", formatPrice(subtotal))
                    SummaryRow(
                        "Descuento ($discountPercentage%)", "-${formatPrice(discountAmount)}"
                    )

                    Divider()

                    SummaryRow("TOTAL", formatPrice(total), bold = true)
                }
            }
        }

        item {
            Text(
                text = "Estado", style = MaterialTheme.typography.titleMedium
            )
        }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                PaymentStatusOption(
                    text = "Pendiente",
                    selected = paymentStatus == PaymentStatus.PENDING,
                    onClick = {
                        paymentStatus = PaymentStatus.PENDING
                        paymentMethod = null
                    })

                PaymentStatusOption(
                    text = "Pagado",
                    selected = paymentStatus == PaymentStatus.PAID,
                    onClick = { paymentStatus = PaymentStatus.PAID })
            }
        }

        if (paymentStatus == PaymentStatus.PAID) {
            item {
                Text(
                    text = "Método de pago", style = MaterialTheme.typography.titleMedium
                )
            }

            item {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    PaymentMethodOption(
                        text = "Bizum",
                        selected = paymentMethod == PaymentMethod.BIZUM,
                        onClick = { paymentMethod = PaymentMethod.BIZUM })

                    PaymentMethodOption(
                        text = "Efectivo",
                        selected = paymentMethod == PaymentMethod.CASH,
                        onClick = { paymentMethod = PaymentMethod.CASH })
                }
            }
        }

        item {
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notas opcional") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2
            )
        }

        item {
            Button(
                onClick = {
                    appointmentsViewModel.addAppointment(
                        clientId = clientId,
                        date = selectedDate,
                        time = selectedTime,
                        notes = notes,
                        services = selectedServices,
                        discountPercentage = discountPercentage,
                        paymentStatus = paymentStatus,
                        paymentMethod = paymentMethod
                    )
                    onBackClick()
                },
                enabled = paymentStatus == PaymentStatus.PENDING || paymentMethod != null,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Guardar cita")
            }
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

@Composable
private fun ServiceSelectableItem(
    service: Service,
    selectedServices: List<AppointmentService>,
    onSelectedServicesChange: (List<AppointmentService>) -> Unit
) {
    val selectedService = selectedServices.find { it.serviceId == service.id }
    val isSelected = selectedService != null
    val quantity = selectedService?.quantity ?: 1

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isSelected, onCheckedChange = { checked ->
                    if (checked) {
                        onSelectedServicesChange(
                            selectedServices + AppointmentService(
                                serviceId = service.id,
                                serviceName = service.name,
                                price = service.price,
                                quantity = 1
                            )
                        )
                    } else {
                        onSelectedServicesChange(
                            selectedServices.filterNot { it.serviceId == service.id })
                    }
                })

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = service.name, style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = formatPrice(service.price),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            if (isSelected) {
                QuantitySelector(quantity = quantity, onMinusClick = {
                    if (quantity > 1) {
                        onSelectedServicesChange(
                            selectedServices.map {
                                if (it.serviceId == service.id) {
                                    it.copy(quantity = it.quantity - 1)
                                } else {
                                    it
                                }
                            })
                    }
                }, onPlusClick = {
                    onSelectedServicesChange(
                        selectedServices.map {
                            if (it.serviceId == service.id) {
                                it.copy(quantity = it.quantity + 1)
                            } else {
                                it
                            }
                        })
                })
            }
        }
    }
}

@Composable
private fun QuantitySelector(
    quantity: Int, onMinusClick: () -> Unit, onPlusClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedButton(
            onClick = onMinusClick,
            modifier = Modifier.size(36.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text("-")
        }

        Text(
            text = quantity.toString(), modifier = Modifier.padding(horizontal = 10.dp)
        )

        OutlinedButton(
            onClick = onPlusClick,
            modifier = Modifier.size(36.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text("+")
        }
    }
}

@Composable
private fun SummaryRow(
    label: String, value: String, bold: Boolean = false
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label, modifier = Modifier.weight(1f), style = if (bold) {
                MaterialTheme.typography.titleMedium
            } else {
                MaterialTheme.typography.bodyMedium
            }
        )

        Text(
            text = value, style = if (bold) {
                MaterialTheme.typography.titleMedium
            } else {
                MaterialTheme.typography.bodyMedium
            }
        )
    }
}

@Composable
private fun PaymentStatusOption(
    text: String, selected: Boolean, onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onClick() }) {
        RadioButton(
            selected = selected, onClick = onClick
        )

        Text(text)
    }
}

@Composable
private fun PaymentMethodOption(
    text: String, selected: Boolean, onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onClick() }) {
        RadioButton(
            selected = selected, onClick = onClick
        )

        Text(text)
    }
}

private fun formatDate(date: LocalDate): String {
    return "${date.dayOfMonth.toString().padStart(2, '0')}/" + "${
        date.monthNumber.toString().padStart(2, '0')
    }/" + date.year
}

private fun formatTime(time: LocalTime): String {
    return "${time.hour.toString().padStart(2, '0')}:" + time.minute.toString().padStart(2, '0')
}

private fun formatPrice(price: Double): String {
    val rounded = (price * 100).toInt() / 100.0
    return "$rounded €".replace(".", ",")
}