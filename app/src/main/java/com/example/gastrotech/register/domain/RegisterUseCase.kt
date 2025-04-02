package com.example.gastrotech.register.domain

import com.example.gastrotech.register.data.model.ClienteAddResponse
import com.example.gastrotech.register.data.model.ClienteRequest
import com.example.gastrotech.register.data.repository.RegisterRepository
import java.util.regex.Pattern

class RegisterUseCase {
    private val repositoryRegister = RegisterRepository()

    suspend operator fun invoke(
        nombre: String,
        apellido: String,
        correo: String,
        contraseña: String,
        teléfono: String,
        token: String,
    ): Result<ClienteAddResponse> {
        if (nombre.isBlank()) {
            return Result.failure(Exception("El nombre no puede estar vacío"))
        }

        if (apellido.isBlank()) {
            return Result.failure(Exception("El apellido no puede estar vacío"))
        }

        if (!isValidEmail(correo)){
            return Result.failure(Exception("El correo electrónico no es válido"))
        }

        if (contraseña.length < 6) {
            return Result.failure(Exception("La contraseña debe tener al menos 6 caracteres"))
        }

        if (teléfono.length < 10){
            return Result.failure(Exception("El telefono debe tener al menos 10 caracteres"))
        }
        val request = ClienteRequest(
            nombre = nombre,
            apellido = apellido,
            correo = correo,
            contraseña = contraseña,
            teléfono = teléfono,
            token = token
        )

        return repositoryRegister.register(request)
    }


    private fun isValidEmail(email: String): Boolean {
        val emailPattern = Pattern.compile(
            "[a-zA-Z0-9+._%\\-]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
        return emailPattern.matcher(email).matches()
    }
}