package com.example.gastrotech.account.data.repository

import android.hardware.camera2.CameraExtensionSession.StillCaptureLatency
import android.util.Log
import com.example.gastrotech.account.data.model.LoginRequest
import com.example.gastrotech.account.data.model.LoginResponse
import com.example.gastrotech.core.network.RetrofitHelper

class LoginRepository {
    private val loginService = RetrofitHelper.loginService

    suspend fun login(correo: String, contraseña: String): Result<LoginResponse> {
        return try {
            val request = LoginRequest(correo, contraseña)
            Log.e("Success", "LOGINREQUEST $request")

            val response = loginService.login(request)

            if (response.isSuccessful){
                Log.e("Request", "LOGINSUCCESS $request")
                Result.success(response.body()!!)
            } else {
                val errorBody = response.errorBody()?.string()
                Log.e("ERROR", "LOGIN ERROR: Code ${response.code()}, Error: $errorBody")
                Result.failure(Exception("Error: ${response.code()} - $errorBody"))
            }

        } catch (e: Exception) {
            println("LOGIN EXCEPTION: ${e.message}")
            e.printStackTrace()
            Result.failure(e)
        }
    }
}