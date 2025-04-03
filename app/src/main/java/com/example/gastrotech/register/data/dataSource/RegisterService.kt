package com.example.gastrotech.register.data.dataSource

import com.example.gastrotech.register.data.model.ClienteAddResponse
import com.example.gastrotech.register.data.model.ClienteRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("crear-clientes")
    suspend fun addCliente(@Body cliente: ClienteRequest): Response<ClienteAddResponse>
}