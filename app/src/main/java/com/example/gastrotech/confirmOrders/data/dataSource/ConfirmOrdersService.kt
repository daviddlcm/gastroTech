package com.example.gastrotech.confirmOrders.data.dataSource

import com.example.gastrotech.confirmOrders.data.model.ConfirmOrdersRequest
import com.example.gastrotech.confirmOrders.data.model.ConfirmOrdersResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ConfirmOrdersService {
    @POST("pedidos")
    suspend fun addPedido(@Body pedido: ConfirmOrdersRequest?): Response<ConfirmOrdersResponse>
}