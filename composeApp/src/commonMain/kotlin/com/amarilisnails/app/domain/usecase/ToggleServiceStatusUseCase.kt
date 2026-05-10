package com.amarilisnails.app.domain.usecase

import com.amarilisnails.app.domain.repository.ServiceRepository

class ToggleServiceStatusUseCase(
    private val repository: ServiceRepository
) {
    operator fun invoke(id: String) {
        repository.toggleServiceStatus(id)
    }
}