package com.example.gastrotech.home.data.dataSource


import com.example.gastrotech.home.data.model.ComidaResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeService {
    @GET("productos")
    suspend fun getComidas(): Response<ComidaResponse>
}