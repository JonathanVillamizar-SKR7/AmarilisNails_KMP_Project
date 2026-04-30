package com.amarilisnails.app.navigation

sealed class Route(val route: String) {
    data object Home : Route("home")
    data object Clients : Route("clients")
    data object ClientDetail : Route("client_detail")
    data object Appointments : Route("appointments")
    data object Services : Route("services")
    data object MonthlySummary : Route("monthly_summary")
}