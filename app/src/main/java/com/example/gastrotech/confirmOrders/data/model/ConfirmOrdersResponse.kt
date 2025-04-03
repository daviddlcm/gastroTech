package com.example.gastrotech.confirmOrders.data.model

import com.google.gson.annotations.SerializedName

data class ConfirmOrdersResponse(
    @SerializedName("message")
    val message: String
)
