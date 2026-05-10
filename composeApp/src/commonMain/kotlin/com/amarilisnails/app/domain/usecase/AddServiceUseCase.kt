package com.amarilisnails.app.domain.usecase

import com.amarilisnails.app.domain.model.Service
import com.amarilisnails.app.domain.repository.ServiceRepository

class AddServiceUseCase(
    private val repository: ServiceRepository
) {
    operator fun invoke(service: Service) {
        repository.addService(service)
    }
}