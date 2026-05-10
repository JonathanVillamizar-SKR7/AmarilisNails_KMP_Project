package com.amarilisnails.app.domain.usecase

import com.amarilisnails.app.domain.model.Service
import com.amarilisnails.app.domain.repository.ServiceRepository

class GetServicesUseCase(
    private val repository: ServiceRepository
) {
    operator fun invoke(): List<Service> {
        return repository.getServices()
    }
}