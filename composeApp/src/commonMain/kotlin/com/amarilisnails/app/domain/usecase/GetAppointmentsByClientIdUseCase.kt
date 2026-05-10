package com.amarilisnails.app.domain.usecase

import com.amarilisnails.app.domain.model.Appointment
import com.amarilisnails.app.domain.repository.AppointmentRepository

class GetAppointmentsByClientIdUseCase(
    private val repository: AppointmentRepository
) {
    operator fun invoke(clientId: String): List<Appointment> {
        return repository.getAppointmentsByClientId(clientId)
    }
}