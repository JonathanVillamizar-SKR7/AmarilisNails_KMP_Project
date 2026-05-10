package com.amarilisnails.app.domain.repository

import com.amarilisnails.app.domain.model.Appointment

interface AppointmentRepository {
    fun getAppointments(): List<Appointment>
    fun getAppointmentsByClientId(clientId: String): List<Appointment>
    fun addAppointment(appointment: Appointment)
}
