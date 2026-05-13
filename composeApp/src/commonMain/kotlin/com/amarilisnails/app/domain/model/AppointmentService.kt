package com.amarilisnails.app.domain.model

data class AppointmentService(
    val serviceId: String, val serviceName: String, val price: Double, val quantity: Int = 1
)
