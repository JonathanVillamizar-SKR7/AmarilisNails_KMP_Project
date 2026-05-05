package com.amarilisnails.app.presentation.appointments

import androidx.lifecycle.ViewModel
import com.amarilisnails.app.domain.model.Appointment
import com.amarilisnails.app.domain.model.AppointmentStatus
import com.amarilisnails.app.domain.usecase.AddAppointmentUseCase
import com.amarilisnails.app.domain.usecase.GetAppointmentsByClientIdUseCase
import com.amarilisnails.app.domain.usecase.GetAppointmentsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

class AppointmentsViewModel(
    private val getAppointmentsUseCase: GetAppointmentsUseCase,
    private val getAppointmentsByClientIdUseCase: GetAppointmentsByClientIdUseCase,
    private val addAppointmentUseCase: AddAppointmentUseCase
) : ViewModel() {

    private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments = _appointments.asStateFlow()

    init {
        loadAppointments()
    }

    fun loadAppointments() {
        _appointments.value = getAppointmentsUseCase()
    }

    fun getAppointmentsByClientId(clientId: String): List<Appointment> {
        return getAppointmentsByClientIdUseCase(clientId)
    }

    fun addAppointment(
        clientId: String, date: LocalDate, time: LocalTime, notes: String
    ) {
        val appointment = Appointment(
            id = "appointment_${Random.nextLong()}",
            clientId = clientId,
            date = date,
            time = time,
            notes = notes,
            status = AppointmentStatus.SCHEDULED
        )

        addAppointmentUseCase(appointment)
        loadAppointments()
    }

    fun getAppointmentsByDate(date: LocalDate): List<Appointment> {
        return getAppointmentsUseCase().filter { it.date == date }.sortedBy { it.time }
    }
}