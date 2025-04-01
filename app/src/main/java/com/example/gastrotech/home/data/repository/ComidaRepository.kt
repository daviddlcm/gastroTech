package com.example.gastrotech.home.data.repository

import android.util.Log
import com.example.gastrotech.core.network.RetrofitHelper
import com.example.gastrotech.home.data.model.ComidaRequest

class ComidaRepository() {
    private val comidaService = RetrofitHelper.homeService

    suspend fun getComidas(): Result<List<ComidaRequest>> {
        return try {
            val response = comidaService.getComidas()
            if (response.isSuccessful){
                response.body()?.let { result ->
                    Log.d("Debug", "Respuesta de la API: $result")
                    Result.success(result.comidas)
                } ?: Result.failure(Exception("Error: Respuesta vacia"))
            } else {
                val errorMessage = response.errorBody()?.toString()  ?: "Hubo un error"
                Log.d("Movie repository", "Error en la respuesta: $errorMessage")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Log.e("Comida repository", "Excepcion al obtener las comidas", e)
            Result.failure(e)
        }
    }
}