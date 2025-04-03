package com.example.gastrotech.account.data.model

data class LoginResponse(
    val message: String,
    val id_cliente: Int,
    val token: String
)
