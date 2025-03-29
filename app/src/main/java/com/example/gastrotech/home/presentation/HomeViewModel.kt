package com.example.gastrotech.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gastrotech.home.data.model.Comida

class HomeViewModel():ViewModel() {

    val listaDeComidas = listOf(
        Comida("chilaquiles",50,"description"),
        Comida("chicarron",55,"description"),
        Comida("tacos",70,"description"),
        Comida("enchiladas",45,"description"),
        Comida("huevos",50,"description"),
        Comida("hot cakes",70,"description")
    )

    var _comidas = MutableLiveData<List<Comida>>()
    var comidas :LiveData<List<Comida>> = _comidas

    suspend fun onGetMenu(){
        _comidas.value = listaDeComidas
    }

}