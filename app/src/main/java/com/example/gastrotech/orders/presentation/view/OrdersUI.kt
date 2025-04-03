package com.example.gastrotech.orders.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import com.example.gastrotech.orders.presentation.viewModel.PedidosViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gastrotech.core.dataStore.AuthManager
import com.example.gastrotech.orders.data.model.PedidosRequest
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun OrdersScreen(pedidosViewModel: PedidosViewModel) {
    val pedidos by pedidosViewModel.pedidos.observeAsState(emptyList())
    val isLoading by pedidosViewModel.isLoading.observeAsState(false)
    val error by pedidosViewModel.error.observeAsState()

    LaunchedEffect(Unit) {
        AuthManager.getUserId()?.let { idCliente ->
            pedidosViewModel.fetchPedidos(idCliente = idCliente)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Mis Pedidos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        when {
            isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            !error.isNullOrEmpty() -> {
                Text(
                    text = "Error: $error",
                    color = Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            pedidos.isEmpty() -> {
                Text(
                    text = "No hay pedidos disponibles.",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            else -> {
                LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                    items(pedidos) { pedido ->
                        PedidoItem(pedido)
                    }
                }
            }
        }
    }
}

@Composable
fun PedidoItem(pedido: PedidosRequest) {
    val estatusColor = when (pedido.estatus) {
        "PENDIENTE" -> Color(0xFFFFEFB0)
        "REALIZADO" -> Color(0xFF4CAF50)
        else -> Color(0xFFE0E0E0)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Pedido #${pedido.id_pedido}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = formatFecha(pedido.pedido_fecha),
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                Box(
                    modifier = Modifier
                        .background(
                            color = estatusColor,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = pedido.estatus,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (pedido.estatus == "Realizado") Color.White else Color(0xFF8A6E00)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "1x ${pedido.detalle_pedido}",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$ ${pedido.total}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@SuppressLint("NewApi")
fun formatFecha(fechaISO: String): String {
    return try {
        val instant = Instant.parse(fechaISO)
        val zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm", Locale("es", "ES"))
        zonedDateTime.format(formatter)
    } catch (e: Exception) {
        "Fecha inválida"
    }
}