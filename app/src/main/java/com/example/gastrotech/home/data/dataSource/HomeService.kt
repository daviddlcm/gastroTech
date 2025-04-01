package com.example.gastrotech.home.data.dataSource


import com.example.gastrotech.home.data.model.ComidaResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeService {
    @GET("v3/e95b5a51-9862-4c52-9817-8f81046e5fb6")
    suspend fun getComidas(): Response<ComidaResponse>
}