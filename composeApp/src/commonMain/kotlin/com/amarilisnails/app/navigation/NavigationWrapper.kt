package com.amarilisnails.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.amarilisnails.app.data.local.InMemoryAppointmentRepository
import com.amarilisnails.app.data.local.InMemoryClientRepository
import com.amarilisnails.app.domain.usecase.AddAppointmentUseCase
import com.amarilisnails.app.domain.usecase.AddClientUseCase
import com.amarilisnails.app.domain.usecase.GetAppointmentsByClientIdUseCase
import com.amarilisnails.app.domain.usecase.GetAppointmentsUseCase
import com.amarilisnails.app.domain.usecase.GetClientByIdUseCase
import com.amarilisnails.app.domain.usecase.GetClientsUseCase
import com.amarilisnails.app.presentation.appointments.AppointmentFormScreen
import com.amarilisnails.app.presentation.appointments.AppointmentsViewModel
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

    val appointmentRepository = remember { InMemoryAppointmentRepository() }

    val appointmentsViewModel = remember {
        AppointmentsViewModel(
            getAppointmentsUseCase = GetAppointmentsUseCase(appointmentRepository),
            getAppointmentsByClientIdUseCase = GetAppointmentsByClientIdUseCase(
                appointmentRepository
            ),
            addAppointmentUseCase = AddAppointmentUseCase(appointmentRepository)
        )
    }

    var selectedClientId by remember { mutableStateOf<String?>(null) }
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
                appointmentsViewModel = appointmentsViewModel,
                onBackClick = { currentScreen = "clients" },
                onNewAppointmentClick = {
                    selectedClientId = selectedClient?.id
                    currentScreen = "appointment_form"
                })
        }

        "appointment_form" -> {
            if (selectedClientId != null) {
                AppointmentFormScreen(
                    clientId = selectedClientId!!,
                    viewModel = appointmentsViewModel,
                    onBackClick = {
                        currentScreen = "client_detail"
                    })
            }
        }
    }
}