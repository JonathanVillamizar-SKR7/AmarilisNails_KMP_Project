package com.amarilisnails.app.domain.usecase

import com.amarilisnails.app.domain.model.Appointment
import com.amarilisnails.app.domain.repository.AppointmentRepository

class AddAppointmentUseCase(
    private val repository: AppointmentRepository
) {
    operator fun invoke(appointment: Appointment) {
        repository.addAppointment(appointment)
    }
}