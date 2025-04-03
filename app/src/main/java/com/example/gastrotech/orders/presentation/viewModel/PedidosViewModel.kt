package com.example.gastrotech.orders.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gastrotech.orders.data.model.PedidosRequest
import com.example.gastrotech.orders.domain.GetPedidosUseCase
import kotlinx.coroutines.launch

class PedidosViewModel(): ViewModel() {
    private val getPedidosUseCase = GetPedidosUseCase()

    private val _pedidos = MutableLiveData<List<PedidosRequest>>()
    val pedidos: LiveData<List<PedidosRequest>> = _pedidos

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun fetchPedidos(idCliente: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = getPedidosUseCase(idCliente)
                if (result.isSuccess) {
                    val pedidosList = result.getOrDefault(emptyList())
                    Log.d("OrdersViewModel", "Pedidos obtenidos: $pedidosList")
                    _pedidos.postValue(pedidosList)
                } else {
                    _error.postValue("Error al obtener los pedidos")
                }
            } catch (e: Exception) {
                Log.e("OrdersViewModel", "Excepción al obtener pedidos: $e")
                _error.postValue(e.message)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}