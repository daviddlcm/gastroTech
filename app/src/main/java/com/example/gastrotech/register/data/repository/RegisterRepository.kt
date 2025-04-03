package com.example.gastrotech.register.data.repository

import com.example.gastrotech.core.network.RetrofitHelper
import com.example.gastrotech.register.data.model.ClienteAddResponse
import com.example.gastrotech.register.data.model.ClienteRequest

class RegisterRepository() {
    private val registerService = RetrofitHelper.registerService

    suspend fun register(registerRequest: ClienteRequest): Result<ClienteAddResponse> {
        return try {
            val response = registerService.addCliente(registerRequest)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}