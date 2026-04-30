package com.amarilisnails.app.data.local

import com.amarilisnails.app.domain.model.Client
import com.amarilisnails.app.domain.repository.ClientRepository

class InMemoryClientRepository : ClientRepository {

    private val clients = mutableListOf<Client>()

    override fun getClients(): List<Client> {
        return clients.toList()
    }

    override fun getClientById(id: String): Client? {
        return clients.find { it.id == id }
    }

    override fun addClient(client: Client) {
        clients.add(client)
    }
}
