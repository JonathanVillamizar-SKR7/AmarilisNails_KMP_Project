package com.amarilisnails.app.domain.model

data class Appointment(
    val id: String,
    val clientId: String,
    val date: String,
    val hour: String,
    val notes: String = "",
    val status: AppointmentStatus = AppointmentStatus.SCHEDULED
)

enum class AppointmentStatus {
    SCHEDULED, COMPLETE, CANCELLED
}