# AmarilisNails KMP PROJECT

AmarilisNails es una aplicación multiplataforma desarrollada con Kotlin Multiplatform para la gestión diaria de un negocio de manicura.

El proyecto está pensado para uso real de una manicurista, permitiendo organizar citas, gestionar fichas de clientas, registrar servicios realizados, controlar pagos y consultar la facturación mensual desde distintos dispositivos.

## Objetivo

El objetivo principal de AmarilisNails es digitalizar y simplificar la gestión del trabajo diario de una manicurista, sustituyendo métodos manuales como agendas físicas, notas dispersas o registros informales de cobros.

La aplicación busca ofrecer una experiencia simple, rápida y visualmente agradable, para que la usuaria pueda adaptarse fácilmente y utilizarla como herramienta principal de trabajo.

## Problema que resuelve

En muchos pequeños negocios de estética, la información diaria suele estar repartida entre conversaciones de WhatsApp, agendas en papel y anotaciones manuales. Esto dificulta:

- llevar el control de citas
- consultar el historial de una clienta
- saber qué servicios se realizaron
- controlar pagos pendientes
- conocer la facturación real del mes
- aplicar programas de fidelización de forma ordenada

AmarilisNails centraliza toda esta información en una única aplicación.

## Usuario objetivo

La aplicación está pensada inicialmente para una única usuaria: una manicurista autónoma que necesita una herramienta privada y sencilla para organizar su negocio.

## Funcionalidades principales

### Gestión de citas
- Crear nuevas citas
- Consultar citas por día
- Consultar citas por mes
- Ver historial general de citas

### Fichas de clientas
- Nombre
- Apellido
- Referencia
- Número de contacto
- Historial de citas
- Historial de servicios
- Estado de pagos
- Sellos acumulados

### Registro de servicios realizados
Al finalizar una cita, la app permitirá:
- seleccionar los servicios realizados
- calcular el total automáticamente
- marcar el estado del pago
- indicar el método de pago:
  - Bizum
  - Efectivo

### Tarifas configurables
La aplicación incluirá un catálogo editable de servicios con su precio correspondiente, por ejemplo:
- Semipermanente
- Acrílico
- SoftGel
- Relleno
- Decoración
- Retirada

### Facturación mensual
La app permitirá consultar:
- número de citas del mes
- importe facturado
- importe cobrado
- importe pendiente de cobro

### Sistema de fidelización
- Cada visita realizada suma 1 sello
- Al completar 6 visitas, la séptima recibe un 20% de descuento

### Sincronización entre dispositivos
La aplicación estará diseñada para ser utilizada tanto en iPhone como en tablet Android, manteniendo la información sincronizada entre ambos dispositivos.

## Stack tecnológico previsto

- Kotlin Multiplatform
- Compose Multiplatform
- Room para persistencia local
- Supabase para sincronización en la nube
- Koin para inyección de dependencias
- Kotlinx Serialization
- Kotlinx DateTime

## Arquitectura prevista

El proyecto seguirá una arquitectura por capas:

- `presentation`: pantallas, estados de UI y ViewModels
- `domain`: entidades, reglas de negocio y casos de uso
- `data`: repositorios, base de datos local y sincronización remota

## Estructura funcional inicial

- Home / Dashboard
- Agenda de citas
- Listado de clientas
- Detalle de clienta
- Registro de cita
- Gestión de servicios y tarifas
- Resumen mensual
- Configuración

## Estado del proyecto

Actualmente el proyecto se encuentra en fase de planificación y definición funcional.

## Roadmap inicial

### Fase 1
- Definición de requisitos
- Diseño de pantallas principales
- Modelado de datos
- Configuración del proyecto KMP

### Fase 2
- Implementación de persistencia local
- Implementación de fichas de clientas
- Implementación de agenda de citas
- Implementación de servicios y tarifas

### Fase 3
- Implementación de cobros y métodos de pago
- Implementación de facturación mensual
- Implementación del sistema de sellos

### Fase 4
- Sincronización cloud entre dispositivos
- Mejoras de UX/UI
- Testing y validación con usuaria real

## Enfoque del proyecto

AmarilisNails no está planteado solo como una práctica técnica, sino como una herramienta real de uso diario, enfocada en la simplicidad, la utilidad y la experiencia de usuario.
