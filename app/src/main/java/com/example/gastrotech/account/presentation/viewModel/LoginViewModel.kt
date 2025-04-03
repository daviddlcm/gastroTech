package com.example.gastrotech.account.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gastrotech.account.domain.LoginUseCase
import com.example.gastrotech.core.dataStore.AuthManager
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val loginUseCase = LoginUseCase()

    private val _correo = MutableLiveData<String>("")
    val correo: LiveData<String> = _correo

    private val _contraseña = MutableLiveData<String>("")
    val contraseña: LiveData<String> = _contraseña

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val _isLoggedIn = MutableLiveData<Boolean>(false)
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

    fun onCorreoChanged(correo: String) {
        _correo.value = correo
    }

    fun onContraseñaChanged(password: String) {
        _contraseña.value = password
    }

    fun onLoginClicked() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val result = loginUseCase(
                correo = _correo.value ?: "",
                contraseña = _contraseña.value ?: ""
            )

            _isLoading.value = false

            result.fold(
                onSuccess = {
                    AuthManager.setToken(it.token)
                    AuthManager.setUserId(it.id_cliente)
                    _isLoggedIn.value = true
                    Log.d("LoginState", "isLoggedIn = $_isLoggedIn")
                },
                onFailure = {
                    _errorMessage.value = it.message
                }
            )
        }
    }

    fun clearError(){
        _errorMessage.value = null
    }

    fun logout() {
        _isLoggedIn.value = false
        _correo.value = ""
        _contraseña.value = ""
    }
}