package com.example.gastrotech.register.data.model

import com.google.gson.annotations.SerializedName

data class ClienteAddResponse(
    @SerializedName("message")
    val message: String,
    val token: String
)
