package com.example.gastrotech.orders.data.dataSource

import com.example.gastrotech.orders.data.model.PedidosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PedidosService {
    @GET("pedidos/{id_cliente}")
    suspend fun getPedidos(@Path("id_cliente") id_cliente: Int): Response<PedidosResponse>
}