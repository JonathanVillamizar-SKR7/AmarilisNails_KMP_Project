package com.amarilisnails.app.domain.usecase

import com.amarilisnails.app.domain.model.Client
import com.amarilisnails.app.domain.repository.ClientRepository

class AddClientUseCase(
    private val repository: ClientRepository
) {
    operator fun invoke(client: Client) {
        repository.addClient(client)
    }
}