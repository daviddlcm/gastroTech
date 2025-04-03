package com.example.gastrotech.orders.domain

import com.example.gastrotech.orders.data.model.PedidosRequest
import com.example.gastrotech.orders.data.repository.PedidosRepository

class GetPedidosUseCase {

    private val repository = PedidosRepository()
    suspend operator fun invoke(idCliente: Int): Result<List<PedidosRequest>> {
        return repository.getPedidos(idCliente)
    }
}