package com.amarilisnails.app.presentation.services

import androidx.lifecycle.ViewModel
import com.amarilisnails.app.domain.model.Service
import com.amarilisnails.app.domain.usecase.AddServiceUseCase
import com.amarilisnails.app.domain.usecase.GetServicesUseCase
import com.amarilisnails.app.domain.usecase.ToggleServiceStatusUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class ServicesViewModel(
    private val getServicesUseCase: GetServicesUseCase,
    private val addServiceUseCase: AddServiceUseCase,
    private val toggleServiceStatusUseCase: ToggleServiceStatusUseCase
) : ViewModel() {

    private val _services = MutableStateFlow<List<Service>>(emptyList())
    val services = _services.asStateFlow()

    init {
        loadServices()
    }

    private fun loadServices() {
        _services.value = getServicesUseCase()
    }

    fun addService(name: String, price: Double) {
        val service = Service(
            id = "service_${Random.nextLong()}", name = name, price = price, isActive = true
        )

        addServiceUseCase(service)
        loadServices()
    }

    fun toggleServiceStatus(id: String) {
        toggleServiceStatusUseCase(id)
        loadServices()
    }
}