package com.example.gastrotech.register.data.model

data class ClienteRequest(
    val nombre: String,
    val apellido: String,
    val correo: String,
    val contraseña: String,
    val teléfono: String,
    val token: String
)
