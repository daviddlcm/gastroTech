package com.example.gastrotech.core.network

import com.example.gastrotech.home.data.dataSource.HomeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
   // private val BASE_URL = "http://192.168.100.19:8080/"
       private val BASE_URL = "https://run.mocky.io/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

      val homeService: HomeService by lazy {
          retrofit.create(HomeService::class.java)
      }
}