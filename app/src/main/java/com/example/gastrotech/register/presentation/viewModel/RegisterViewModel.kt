package com.example.gastrotech.register.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gastrotech.register.data.repository.RegisterRepository
import com.example.gastrotech.register.domain.RegisterUseCase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    private val repository = RegisterRepository()
    private val registerUseCase = RegisterUseCase()

    private val _nombre = MutableLiveData<String>("")
    val nombre: LiveData<String> = _nombre

    private val _apellido = MutableLiveData<String>("")
    val apellido: LiveData<String> = _apellido

    private val _correo = MutableLiveData<String>("")
    val correo: LiveData<String> = _correo

    private val _contraseña = MutableLiveData<String>("")
    val contraseña: LiveData<String> = _contraseña

    private val _teléfono = MutableLiveData<String>("")
    val teléfono: LiveData<String> = _teléfono

    private val _token = MutableLiveData<String>("")
    val token: LiveData<String> =_token

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val _isRegistered = MutableLiveData<Boolean>(false)
    val isRegistered: LiveData<Boolean> = _isRegistered

    init {
        getDeviceToken()
    }

    fun onNombreChanged(value: String) {
        _nombre.value = value
    }

    fun onApellidoChanged(value: String){
        _apellido.value = value
    }

    fun onCorreoChanged(value: String){
        _correo.value = value
    }

    fun onContraseñaChanged(value: String){
        _contraseña.value = value
    }

    fun onTelefonoChanged(value: String){
        _teléfono.value = value
    }

    fun onRegisterClicked() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val token = _token.value ?: ""

            try {
                val result = registerUseCase(
                    nombre = _nombre.value ?: "",
                    apellido = _apellido.value ?: "",
                    correo = _correo.value ?: "",
                    contraseña = _contraseña.value ?: "",
                    teléfono = _teléfono.value ?: "",
                    token = token
                )
                if (result.isSuccess) {
                    _isRegistered.value = true
                } else {
                    _errorMessage.value = result.exceptionOrNull()?.message ?: "Error desconocido"
                }
            }  catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error desconocido"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun getDeviceToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _token.value = task.result
                Log.d("Token", "Token: ${task.result}")
            } else {
                Log.e("Token", "Error al obtener el token: ${task.exception}")
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }

}