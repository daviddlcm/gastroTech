package com.example.gastrotech.core.network

import com.example.gastrotech.account.data.dataSource.LoginService
import com.example.gastrotech.confirmOrders.data.dataSource.ConfirmOrdersService
import com.example.gastrotech.home.data.dataSource.HomeService
import com.example.gastrotech.orders.data.dataSource.PedidosService
import com.example.gastrotech.register.data.dataSource.RegisterService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
     private val BASE_URL = "http://192.168.100.19:8080/"
     //private val BASE_URL = "http://10.0.2.2:8080/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

      val homeService: HomeService by lazy {
          retrofit.create(HomeService::class.java)
      }

    val registerService: RegisterService by lazy {
        retrofit.create(RegisterService::class.java)
    }

    val loginService: LoginService by lazy {
        retrofit.create(LoginService::class.java)
    }

    val pedidosService: PedidosService by lazy {
        retrofit.create(PedidosService::class.java)
    }

    val confirmOrdersService: ConfirmOrdersService by lazy {
        retrofit.create(ConfirmOrdersService::class.java)
    }
}