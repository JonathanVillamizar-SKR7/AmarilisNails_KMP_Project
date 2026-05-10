package com.amarilisnails.app.domain.repository

import com.amarilisnails.app.domain.model.Service

interface ServiceRepository {
    fun getServices(): List<Service>
    fun addService(service: Service)
    fun toggleServiceStatus(id: String)
}