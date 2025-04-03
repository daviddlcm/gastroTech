package com.example.gastrotech.confirmOrders.data.model

data class ConfirmOrdersRequest(
    val total: String,
    val detalle_pedido: String,
    val id_cliente: Int,
    val created_by: Int,
    val id_administrador: Int
)
