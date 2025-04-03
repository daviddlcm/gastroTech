package com.example.gastrotech.orders.data.model

import com.google.gson.annotations.SerializedName

data class PedidosRequest(
    val id_pedido: Int,
    val id_cliente: Int,
    val pedido_fecha: String,
    val total: Int,
    val detalle_pedido: String,
    val estatus: String
)


data class PedidosResponse(
    @SerializedName("data")
    val pedidos: List<PedidosRequest>
)