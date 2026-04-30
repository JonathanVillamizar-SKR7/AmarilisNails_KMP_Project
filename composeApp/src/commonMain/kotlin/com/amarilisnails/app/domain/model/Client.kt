package com.amarilisnails.app.domain.model

data class Client(
    val id: String,
    val name: String,
    val surname: String,
    val phone: String,
    val reference: String = "",
    val createdAt: Long = 0L
)