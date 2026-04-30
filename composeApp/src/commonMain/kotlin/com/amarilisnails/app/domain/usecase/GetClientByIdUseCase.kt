package com.amarilisnails.app.domain.usecase

import com.amarilisnails.app.domain.model.Client
import com.amarilisnails.app.domain.repository.ClientRepository

class GetClientByIdUseCase(
    private val repository: ClientRepository
) {
    operator fun invoke(id: String): Client? {
        return repository.getClientById(id)
    }
}