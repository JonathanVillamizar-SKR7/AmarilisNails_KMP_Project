package com.amarilisnails.app.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime


data class Appointment(
    val id: String,
    val clientId: String,
    val date: LocalDate,
    val time: LocalTime,
    val notes: String = "",
    val status: AppointmentStatus = AppointmentStatus.SCHEDULED
)

enum class AppointmentStatus {
    SCHEDULED, COMPLETE, CANCELLED
}