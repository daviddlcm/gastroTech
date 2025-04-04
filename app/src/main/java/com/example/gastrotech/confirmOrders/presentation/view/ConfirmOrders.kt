package com.example.gastrotech.confirmOrders.presentation.view


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gastrotech.confirmOrders.presentation.viewModel.ConfirmOrdersViewModel
import com.example.gastrotech.core.dataStore.AuthManager
import com.example.gastrotech.core.service.Vibration
import com.example.gastrotech.home.data.model.ComidaRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ConfirmDialog(
    comida: ComidaRequest,
    onDismiss: () -> Unit,
    confirmOrdersViewModel: ConfirmOrdersViewModel = viewModel()
) {
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    val success by confirmOrdersViewModel.sucess.observeAsState(false)
    val context = LocalContext.current


    Box(modifier = Modifier.fillMaxSize()) {
        AlertDialog(
            onDismissRequest = { if (!isLoading) onDismiss() },
            title = { Text(text = "Confirmar Pedido") },
            text = {
                Column {
                    Text(text = "Producto: ${comida.nombre_producto}")
                    if (message.isNotEmpty()) {
                        Text(
                            text = message,
                            color = when {
                                message.contains("Error") -> Color.Red
                                message == "Debe iniciar sesión para confirmar el pedido." -> Color.Red
                                else -> Color(0xFF388E3C)
                            }
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (!isLoading) {
                            isLoading = true
                            val userId = AuthManager.getUserId()
                            if (userId == null) {
                                scope.launch {
                                    message = "Debe iniciar sesión para confirmar el pedido."
                                    isLoading = false
                                    delay(2000)
                                    onDismiss()
                                }
                                return@Button
                            }

                            confirmOrdersViewModel.setSelectedComida(comida)
                            confirmOrdersViewModel.confirmarPedido(
                                onSuccess = {
                                    Vibration(context).vibrate()
                                    scope.launch {
                                        message = "Pedido enviado con éxito"
                                        isLoading = false
                                        delay(2000)
                                        onDismiss()
                                    }
                                },
                                onError = { mensaje ->
                                    scope.launch {
                                        message = "Error: $mensaje"
                                        isLoading = false
                                    }
                                }
                            )
                        }
                    },
                    enabled = !isLoading
                ) {
                    Text(if (isLoading) "Enviando..." else "Aceptar")
                }
            },
            dismissButton = {
                Button(onClick = { if (!isLoading) onDismiss() }) {
                    Text("Cancelar")
                }
            }
        )
    }
}



