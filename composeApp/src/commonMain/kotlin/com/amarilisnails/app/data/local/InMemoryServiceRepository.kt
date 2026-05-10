package com.amarilisnails.app.data.local

import com.amarilisnails.app.domain.model.Service
import com.amarilisnails.app.domain.repository.ServiceRepository

class InMemoryServiceRepository : ServiceRepository {

    private val services = mutableListOf(

        // MANICURA

        Service(
            id = "service_1", name = "Manicura convencional", price = 15.50
        ),

        Service(
            id = "service_2", name = "Manicura con base rubber", price = 25.50
        ),

        Service(
            id = "service_3", name = "Manicura con nivelación Builder", price = 27.50
        ),

        // ACRILICO

        Service(
            id = "service_4", name = "Acrílico primera puesta", price = 38.50
        ),

        Service(
            id = "service_5", name = "Mantenimiento de acrílico", price = 32.50
        ),

        Service(
            id = "service_6", name = "Mantenimiento de acrílico extra largo", price = 35.50
        ),

        // SOFT GEL

        Service(
            id = "service_7", name = "Sistema Soft Gel", price = 35.00
        ),

        // PEDICURA

        Service(
            id = "service_8", name = "Pedicura express", price = 35.00
        ),

        Service(
            id = "service_9", name = "Pedicura Pro", price = 40.00
        ),

        Service(
            id = "service_10", name = "Pedicura Pro con parafina", price = 45.00
        ),

        // EXTRAS

        Service(
            id = "service_11", name = "Decoración extra", price = 2.50
        ),

        Service(
            id = "service_12", name = "Efectos", price = 2.50
        ),

        Service(
            id = "service_13", name = "Pedrería", price = 2.50
        ),

        Service(
            id = "service_14", name = "Flores 3D", price = 2.50
        ),

        // PARAFINA

        Service(
            id = "service_15", name = "Servicio de parafina", price = 12.50
        ),

        // BONOS

        Service(
            id = "service_16", name = "Pack 8 parafinas", price = 80.00
        )
    )

    override fun getServices(): List<Service> {
        return services.toList()
    }

    override fun addService(service: Service) {
        services.add(service)
    }

    override fun toggleServiceStatus(id: String) {
        val index = services.indexOfFirst { it.id == id }
        if (index != -1) {
            val service = services[index]
            services[index] = service.copy(isActive = !service.isActive)
        }
    }
}