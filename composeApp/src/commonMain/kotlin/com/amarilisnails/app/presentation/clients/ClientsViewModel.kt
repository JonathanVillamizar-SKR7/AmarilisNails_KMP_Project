package com.amarilisnails.app.presentation.clients

import androidx.lifecycle.ViewModel
import com.amarilisnails.app.domain.model.Client
import com.amarilisnails.app.domain.usecase.AddClientUseCase
import com.amarilisnails.app.domain.usecase.GetClientByIdUseCase
import com.amarilisnails.app.domain.usecase.GetClientsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class ClientsViewModel(
    private val getClientsUseCase: GetClientsUseCase,
    private val getClientByIdUseCase: GetClientByIdUseCase,
    private val addClientUseCase: AddClientUseCase
) : ViewModel() {

    private val _clients = MutableStateFlow<List<Client>>(emptyList())
    val clients = _clients.asStateFlow()

    private val _selectedClient = MutableStateFlow<Client?>(null)
    val selectedClient = _selectedClient.asStateFlow()

    init {
        loadClients()
    }

    private fun loadClients() {
        _clients.value = getClientsUseCase()
    }

    fun selectClient(id: String) {
        _selectedClient.value = getClientByIdUseCase(id)
    }

    fun addClient(
        name: String, surname: String, phone: String, reference: String
    ) {
        val client = Client(
            id = "client_${Random.nextLong()}",
            name = name,
            surname = surname,
            phone = phone,
            reference = reference
        )

        addClientUseCase(client)
        loadClients()
    }
}