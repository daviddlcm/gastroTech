package com.example.gastrotech.account.domain

import com.example.gastrotech.account.data.model.LoginResponse
import com.example.gastrotech.account.data.repository.LoginRepository

class LoginUseCase {
    private val repository = LoginRepository()

    suspend operator fun invoke (correo: String, contraseña: String): Result<LoginResponse> {
        if (correo.isBlank()){
            return Result.failure(Exception("El correo no puede estar vacío"))
        }

        if (contraseña.length < 6) {
            return Result.failure(Exception("La contraseña debe tener al menos 6 caracteres"))
        }
        return repository.login(correo, contraseña)
    }
}