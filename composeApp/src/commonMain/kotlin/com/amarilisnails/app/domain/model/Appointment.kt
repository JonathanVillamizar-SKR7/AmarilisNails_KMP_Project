package com.amarilisnails.app.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class Appointment(
    val id: String, val clientId: String,

    val date: LocalDate, val time: LocalTime,

    val notes: String,

    val services: List<AppointmentService> = emptyList(),

    val subtotal: Double = 0.0,

    val discountPercentage: Int = 0,

    val total: Double = 0.0,

    val paymentStatus: PaymentStatus = PaymentStatus.PENDING,

    val paymentMethod: PaymentMethod? = null
)