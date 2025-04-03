package com.example.gastrotech.home.presentation.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.gastrotech.home.data.model.ComidaRequest
import com.example.gastrotech.home.presentation.viewModel.HomeViewModel
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.painter.Painter
import com.example.gastrotech.R
import com.example.gastrotech.confirmOrders.presentation.view.ConfirmDialog
import com.example.gastrotech.confirmOrders.presentation.viewModel.ConfirmOrdersViewModel


@Composable
fun HomeScreen(homeViewModel: HomeViewModel, confirmOrdersViewModel: ConfirmOrdersViewModel) {
    val isLoading by homeViewModel.isLoading.observeAsState(false)
    val comidas by homeViewModel.comidas.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        homeViewModel.onGetMenu()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(7.dp)
    ) {
        Home(comidas, isLoading, confirmOrdersViewModel)
    }
}

@Composable
fun Home(comidas: List<ComidaRequest>, isLoading: Boolean, confirmOrdersViewModel: ConfirmOrdersViewModel) {

    Column {
        Spacer(modifier = Modifier.padding(10.dp))
        HeaderHome()
        Spacer(modifier = Modifier.padding(15.dp))
        MainPromotionImg()
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "Menu", fontWeight = FontWeight.Bold, fontSize = 30.sp)
        Spacer(modifier = Modifier.padding(2.dp))
        if (isLoading){
            Text(
                "Cargando comidas.....",
                color = Color.Black
            )
        } else {
            if (comidas.isNotEmpty()) {
                ShowMenu(comidas = comidas, confirmOrdersViewModel)
            } else{
                Text(
                    text = "No hay comidas disponibles",
                    color = Color.Black
                )
            }
        }

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
    Image(
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
            color = Color.White,
            shape = RoundedCornerShape(16.dp)
        )
            .border(2.dp, Color.White, shape = RoundedCornerShape(16.dp)),
        painter = painterResource(id = R.drawable.burguer),
        contentDescription = "Imagen de promoción",
    )
}

@Composable
fun ShowMenu(comidas: List<ComidaRequest>, confirmOrdersViewModel: ConfirmOrdersViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(comidas) { comida ->
            CardFood(comida, confirmOrdersViewModel)
        }
    }
}


@Composable
fun CardFood(comida: ComidaRequest, confirmOrdersViewModel: ConfirmOrdersViewModel) {
var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                val imagePainter: Painter = rememberAsyncImagePainter(model = comida.imagen)

                Image(
                    painter = imagePainter,
                    contentDescription = "Imagen de comida",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(text = comida.nombre_producto, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "$ ${comida.precio}")
                }
            }
                Text(
                    text = "Pedir",
                    color = Color.Blue,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
                        .clickable {
                            confirmOrdersViewModel.setSelectedComida(comida)
                            showDialog = true
                        }
                )

        }
    }

    if (showDialog) {
        ConfirmDialog(
            comida = comida,
            onDismiss = {
                showDialog = false
                confirmOrdersViewModel.clearSelectedComida()
            }
        )
    }
}
