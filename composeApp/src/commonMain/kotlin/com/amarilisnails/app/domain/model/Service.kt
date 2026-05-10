package com.amarilisnails.app.domain.model

data class Service(
    val id: String,
    val name: String,
    val price: Double,
    val isActive: Boolean = true
)