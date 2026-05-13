package com.amarilisnails.app.presentation.appointments

import com.amarilisnails.app.domain.model.AppointmentService
import com.amarilisnails.app.domain.model.PaymentMethod
import com.amarilisnails.app.domain.model.PaymentStatus
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class AppointmentFormUiState(
    val selectedDate: LocalDate,
    val selectedTime: LocalTime,
    val selectedServices: List<AppointmentService> = emptyList(),
    val subtotal: Double = 0.0,
    val discountPercentage: Int = 0,
    val total: Double = 0.0,
    val paymentStatus: PaymentStatus = PaymentStatus.PENDING,
    val paymentMethod: PaymentMethod? = null,
    val notes: String = ""
)