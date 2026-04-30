package com.amarilisnails.app.domain.repository

import com.amarilisnails.app.domain.model.Client

interface ClientRepository {
    fun getClients(): List<Client>
    fun getClientById(id: String): Client?
    fun addClient(client: Client)
}