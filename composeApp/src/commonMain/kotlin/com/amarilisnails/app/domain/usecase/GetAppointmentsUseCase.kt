package com.amarilisnails.app.domain.usecase

import com.amarilisnails.app.domain.model.Appointment
import com.amarilisnails.app.domain.repository.AppointmentRepository

class GetAppointmentsUseCase(
    private val repository: AppointmentRepository
) {
    operator fun invoke(): List<Appointment> {
        return repository.getAppointments()
    }
}