package com.example.gastrotech.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Comida(val nombre: String)

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(7.dp)
    ) {
        Home()
    }
}

@Composable
fun Home() {
    val listaDeComidas = listOf(
        Comida("Comida 1"),
        Comida("Comida 2"),
        Comida("Comida 3"),
        Comida("Comida 4"),
        Comida("Comida 5"),
        Comida("Comida 6")
    )
    Column {
        Spacer(modifier = Modifier.padding(10.dp))
        HeaderHome()
        Spacer(modifier = Modifier.padding(15.dp))
        MainPromotionImg()
        ShowMenu(comidas = listaDeComidas)
    }
}

@Composable
fun HeaderHome() {
    Text(
        "GastroTech",
        fontSize = 35.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun MainPromotionImg() {
    Text("imagen de promocion de hoy")
}

@Composable
fun ShowMenu(comidas: List<Comida>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(comidas) { comida ->
            CardFood(comida)
        }
    }
}

@Composable
fun CardFood(comida: Comida) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = comida.nombre)
        }
    }
}