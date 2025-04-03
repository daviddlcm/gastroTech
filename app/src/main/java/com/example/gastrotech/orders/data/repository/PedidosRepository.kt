package com.example.gastrotech.orders.data.repository

import android.util.Log
import com.example.gastrotech.core.network.RetrofitHelper
import com.example.gastrotech.orders.data.model.PedidosRequest

class PedidosRepository() {
    private val orderService = RetrofitHelper.pedidosService

    suspend fun getPedidos(idCliente: Int): Result<List<PedidosRequest>> {
        return try {
            val response = orderService.getPedidos(idCliente)
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    Log.d("PedidosRepository", "Respuesta de la API: $result")
                    Result.success(result.pedidos)
                } ?: Result.failure(Exception("Error: Respuesta vacia"))
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Hubo un error"
                Log.e("PedidosRepository", "Error en la respuesta: $errorMessage")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Log.e("PedidosRepository", "Excepción al obtener los pedidos", e)
            Result.failure(e)
        }
    }
}