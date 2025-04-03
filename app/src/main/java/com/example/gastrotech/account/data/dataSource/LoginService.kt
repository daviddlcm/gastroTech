package com.example.gastrotech.account.data.dataSource

import com.example.gastrotech.account.data.model.LoginRequest
import com.example.gastrotech.account.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("auth/login-cliente")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}