package com.amarilisnails.app.presentation.appointments

import androidx.lifecycle.ViewModel
import com.amarilisnails.app.domain.model.Appointment
import com.amarilisnails.app.domain.model.AppointmentService
import com.amarilisnails.app.domain.model.PaymentMethod
import com.amarilisnails.app.domain.model.PaymentStatus
import com.amarilisnails.app.domain.model.Service
import com.amarilisnails.app.domain.usecase.AddAppointmentUseCase
import com.amarilisnails.app.domain.usecase.GetAppointmentsByClientIdUseCase
import com.amarilisnails.app.domain.usecase.GetAppointmentsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlin.random.Random

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
        clientId: String,
        date: LocalDate,
        time: LocalTime,
        notes: String,
        services: List<AppointmentService> = emptyList(),
        discountPercentage: Int = 0,
        paymentStatus: PaymentStatus = PaymentStatus.PENDING,
        paymentMethod: PaymentMethod? = null
    ) {
        val subtotal = services.sumOf { it.price * it.quantity }
        val discountAmount = subtotal * discountPercentage / 100
        val total = subtotal - discountAmount

        val appointment = Appointment(
            id = "appointment_${Random.nextLong()}",
            clientId = clientId,
            date = date,
            time = time,
            notes = notes,
            services = services,
            subtotal = subtotal,
            discountPercentage = discountPercentage,
            total = total,
            paymentStatus = paymentStatus,
            paymentMethod = paymentMethod
        )

        addAppointmentUseCase(appointment)
        loadAppointments()
    }

    fun createAppointmentService(service: Service, quantity: Int): AppointmentService {
        return AppointmentService(
            serviceId = service.id,
            serviceName = service.name,
            price = service.price,
            quantity = quantity
        )
    }
}