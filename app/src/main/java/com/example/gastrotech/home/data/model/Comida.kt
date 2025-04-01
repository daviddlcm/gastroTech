package com.example.gastrotech.home.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ComidaRequest(
    val id_producto: Int,
    val nombre_producto: String,
    val precio: String,
    val descripcion: String,
    val imagen: String,
    val categoria: String,
    val created_by: Int
)

data class ComidaResponse(
    @SerializedName("data")
    val comidas: List<ComidaRequest>
)
