package com.amarilisnails.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform