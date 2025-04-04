package com.example.gastrotech.confirmOrders.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gastrotech.confirmOrders.data.model.ConfirmOrdersRequest
import com.example.gastrotech.confirmOrders.data.repository.ConfirmOrdersRepository
import com.example.gastrotech.core.dataStore.AuthManager
import com.example.gastrotech.core.service.Vibration
import com.example.gastrotech.home.data.model.ComidaRequest
import kotlinx.coroutines.launch

class ConfirmOrdersViewModel() : ViewModel() {

    private val repository = ConfirmOrdersRepository()
    private val _selectedComida = MutableLiveData<ComidaRequest?>()
    val selectedComida: LiveData<ComidaRequest?> = _selectedComida

    private val _success = MutableLiveData<Boolean>(false)
    var sucess: LiveData<Boolean> = _success

    fun setSelectedComida(comida: ComidaRequest) {
        _selectedComida.value = comida
    }

    fun clearSelectedComida() {
        _selectedComida.value = null
    }

    fun confirmarPedido(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val userId = AuthManager.getUserId()
            Log.d("Pedido", "Usuario autenticado: $userId")

            _selectedComida.value?.let { comida ->
                Log.d("Pedido", "Comida seleccionada: ${comida.nombre_producto}, Precio: ${comida.precio}")

                if (userId == null) {
                    Log.e("Pedido", "Error: Necesita haber iniciado sesión para pedir")
                    return@launch
                }

                val confirmOrder = ConfirmOrdersRequest(
                    total = comida.precio,
                    detalle_pedido = comida.nombre_producto,
                    id_cliente = userId,
                    created_by = userId,
                    id_administrador = comida.created_by
                )

                Log.d("Pedido", "Datos del pedido a enviar: $confirmOrder")

                try {
                    val response = repository.addPedidos(confirmOrder)

                    if (response.isSuccess) {
                        Log.d("Pedido", "Pedido enviado con éxito: ${confirmOrder.detalle_pedido}")
                        _success.value = true
                        onSuccess()
                    } else {
                        Log.e("Pedido", "Error al enviar el pedido. Código: ${response}")
                        onError("Error en la API: ${response}")
                    }
                } catch (e: Exception) {
                    Log.e("Pedido", "Error en la solicitud: ${e.message}", e)
                }
            } ?: Log.e("Pedido", "Error: No hay comida seleccionada para realizar el pedido")
        }
    }
}
