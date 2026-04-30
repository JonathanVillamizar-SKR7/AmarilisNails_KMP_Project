package com.amarilisnails.app.navigation

import androidx.compose.runtime.*
import com.amarilisnails.app.data.local.InMemoryClientRepository
import com.amarilisnails.app.domain.usecase.AddClientUseCase
import com.amarilisnails.app.domain.usecase.GetClientByIdUseCase
import com.amarilisnails.app.domain.usecase.GetClientsUseCase
import com.amarilisnails.app.presentation.clients.ClientDetailScreen
import com.amarilisnails.app.presentation.clients.ClientFormScreen
import com.amarilisnails.app.presentation.clients.ClientsScreen
import com.amarilisnails.app.presentation.clients.ClientsViewModel

@Composable
fun NavigationWrapper() {
    val repository = remember { InMemoryClientRepository() }

    val viewModel = remember {
        ClientsViewModel(
            getClientsUseCase = GetClientsUseCase(repository),
            getClientByIdUseCase = GetClientByIdUseCase(repository),
            addClientUseCase = AddClientUseCase(repository)
        )
    }

    var currentScreen by remember { mutableStateOf("clients") }

    when (currentScreen) {
        "clients" -> {
            ClientsScreen(
                viewModel = viewModel,
                onAddClientClick = { currentScreen = "client_form" },
                onClientClick = { clientId ->
                    viewModel.selectClient(clientId)
                    currentScreen = "client_detail"
                })
        }

        "client_form" -> {
            ClientFormScreen(
                viewModel = viewModel, onBackClick = { currentScreen = "clients" })
        }

        "client_detail" -> {
            val selectedClient by viewModel.selectedClient.collectAsState()

            ClientDetailScreen(
                client = selectedClient,
                onBackClick = { currentScreen = "clients" },
                onNewAppointmentClick = { })
        }
    }
}