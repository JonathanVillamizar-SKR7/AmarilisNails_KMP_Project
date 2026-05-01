package com.amarilisnails.app.data.local

import com.amarilisnails.app.domain.model.Appointment
import com.amarilisnails.app.domain.repository.AppointmentRepository

class InMemoryAppointmentRepository : AppointmentRepository {

    private val appointments = mutableListOf<Appointment>()

    override fun getAppointments(): List<Appointment> {
        return appointments.toList()
    }

    override fun getAppointmentsByClientId(clientId: String): List<Appointment> {
        return appointments.filter { it.clientId == clientId }
    }

    override fun addAppointment(appointment: Appointment) {
        appointments.add(appointment)
    }
}