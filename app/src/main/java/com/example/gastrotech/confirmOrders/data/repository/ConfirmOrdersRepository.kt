package com.example.gastrotech.confirmOrders.data.repository

import com.example.gastrotech.confirmOrders.data.model.ConfirmOrdersRequest
import com.example.gastrotech.confirmOrders.data.model.ConfirmOrdersResponse
import com.example.gastrotech.core.network.RetrofitHelper

class ConfirmOrdersRepository() {
    private val confirmOrdersService = RetrofitHelper.confirmOrdersService

    suspend fun addPedidos(confirmOrdersRequest: ConfirmOrdersRequest?): Result<ConfirmOrdersResponse> {
        return try {
            val response = confirmOrdersService.addPedido(confirmOrdersRequest)
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