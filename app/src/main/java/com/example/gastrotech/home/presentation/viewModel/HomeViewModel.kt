package com.example.gastrotech.home.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gastrotech.home.data.model.ComidaRequest
import com.example.gastrotech.home.data.repository.ComidaRepository
import com.example.gastrotech.home.domain.GetComidasUseCase
import java.util.concurrent.Executor

class HomeViewModel():ViewModel() {




   // private val getComidasUseCase = GetComidasUseCase()
    private val comidaRepository = ComidaRepository()

    private val _comidas = MutableLiveData<List<ComidaRequest>>()
    val comidas: LiveData<List<ComidaRequest>> = _comidas

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>(null)
    val error: MutableLiveData<String?> = _error

    suspend fun onGetMenu(){
        _isLoading.value = true
        try {
           val result = comidaRepository.getComidas()
            if (result.isSuccess) {
                val comidaList = result.getOrDefault(emptyList())
                Log.d("Home ViewModel", "Comidas obtenidas: $comidaList")
                _comidas.postValue(comidaList)

                _isLoading.value = false
            }
        } catch (e: Exception) {
            Log.e("HomeViewModel", "Excepción al obtener películas: $e")
        }
    }

}