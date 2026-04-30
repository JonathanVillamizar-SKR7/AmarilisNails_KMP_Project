package com.amarilisnails.app.domain.usecase

import com.amarilisnails.app.domain.model.Client
import com.amarilisnails.app.domain.repository.ClientRepository

class GetClientsUseCase(
    private val repository: ClientRepository
) {
    operator fun invoke(): List<Client> {
        return repository.getClients()
    }
}