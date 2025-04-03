package com.example.gastrotech.register.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gastrotech.register.presentation.viewModel.RegisterViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel, onNavigate: () -> Unit) {

    val nombre by registerViewModel.nombre.observeAsState("")
    val apellidos by registerViewModel.apellido.observeAsState("")
    val correo by registerViewModel.correo.observeAsState("")
    val contraseña by registerViewModel.contraseña.observeAsState("")
    val teléfono by registerViewModel.teléfono.observeAsState("")
    val isLoading by registerViewModel.isLoading.observeAsState(false)
    val errorMessage by registerViewModel.errorMessage.observeAsState(null)
    val isRegistered by registerViewModel.isRegistered.observeAsState(false)
    var isVisible by remember { mutableStateOf(false) }


    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(isRegistered) {
        if (isRegistered){
            coroutineScope.launch {
                snackbarHostState.showSnackbar("Cuenta creada con exito")
                onNavigate()
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SnackbarHost(hostState = snackbarHostState) { data ->
            Snackbar(
                snackbarData = data,
                containerColor = Color(0xFF4CAF50),
                contentColor = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
        }

        TitleText()
        Spacer(modifier = Modifier.height(16.dp))
        NombreField(
            nombre = nombre,
            onNombreChange = { registerViewModel.onNombreChanged(it)}
        )
        Spacer(modifier = Modifier.height(8.dp))
        ApellidosField(
            apellidos = apellidos,
            onApellidosChange = { registerViewModel.onApellidoChanged(it)}
        )
        Spacer(modifier = Modifier.height(16.dp))
        CorreoField(
            correo = correo,
            onCorreoChange = { registerViewModel.onCorreoChanged(it)}
        )
        Spacer(modifier = Modifier.height(16.dp))
        ContraseñaField(
            contraseña = contraseña,
            onContraseñahange = { registerViewModel.onContraseñaChanged(it)},
            isVisible = isVisible,
            onVisibilityChange = { isVisible = !isVisible}
        )
        Spacer(modifier = Modifier.height(16.dp))
        TelefonoField(
            teléfono = teléfono,
            onTeléfonoChange = { registerViewModel.onTelefonoChanged(it)}
        )
        Spacer(modifier = Modifier.height(16.dp))

        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        LoginButton(
            isLoading = isLoading,
            onLoginSuccessClick = {registerViewModel.onRegisterClicked()}
        )
        Spacer(modifier = Modifier.height(16.dp))
        BottonAction(onRegisterClick = onNavigate)

    }

}


@Composable
fun TitleText() {
    Text(
        text = "Crear cuenta",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}

@Composable
fun NombreField(
    nombre: String,
    onNombreChange: (String) -> Unit
) {
    Column (modifier = Modifier.fillMaxWidth()) {
        Text("Nombre", color = Black)
        OutlinedTextField(
            value = nombre,
            onValueChange = onNombreChange,
            label = { Text(text = "Nombre", color = Color.Black) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            shape = RoundedCornerShape(18.dp),
            placeholder = { Text("Emmanuel", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Nombre", tint = Color.Black) },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun ApellidosField(
    apellidos: String,
    onApellidosChange: (String) -> Unit
) {
    Column (modifier = Modifier.fillMaxWidth()) {
        Text("Apellidos", color = Black)
        OutlinedTextField(
            value = apellidos,
            onValueChange = onApellidosChange,
            label = { Text(text = "Apellido", color = Color.Black) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            shape = RoundedCornerShape(18.dp),
            placeholder = { Text("Lucas Morales", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Apellido", tint = Color.Black) },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun CorreoField(
    correo: String,
    onCorreoChange: (String) -> Unit)
{
    Column (modifier = Modifier.fillMaxWidth()) {
        Text("Correo", color = Black)
        OutlinedTextField(
            value = correo,
            onValueChange = onCorreoChange,
            label = { Text(text = "E-mail", color = Color.Black) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            shape = RoundedCornerShape(18.dp),
            placeholder = { Text("223729@ids.upchiapas.edu.mx", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "E-mail", tint = Color.Black) },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}


@Composable
fun ContraseñaField(
    contraseña: String,
    onContraseñahange: (String) -> Unit,
    isVisible: Boolean,
    onVisibilityChange: () -> Unit
) {
    Column (modifier = Modifier.fillMaxWidth()){
        Text("Contraseña", color = Black)
        OutlinedTextField(
            value = contraseña,
            onValueChange = onContraseñahange,
            label = { Text(text = "Contraseña", color = Color.Black) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            shape = RoundedCornerShape(18.dp),
            placeholder = { Text("Ti contraseña", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Contraseña", tint = Color.Black) },
            visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                IconButton(onClick = onVisibilityChange) {
                    Icon(
                        imageVector = if (isVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (isVisible) "Ocultar contraseña" else "Mostrar contraseña",
                        tint = Color.Black
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun TelefonoField(
    teléfono: String,
    onTeléfonoChange: (String) -> Unit
) {
    Column (modifier = Modifier.fillMaxWidth()){
        Text("Teléfono", color = Black)
        OutlinedTextField(
            value = teléfono,
            onValueChange = onTeléfonoChange,
            label = { Text(text = "Teléfono", color = Color.Black) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            shape = RoundedCornerShape(18.dp),
            placeholder = { Text("961 123 4567", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = "Teléfono", tint = Color.Black) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}


@Composable
fun LoginButton(
    isLoading: Boolean,
    onLoginSuccessClick: () -> Unit
) {
    Button(
        onClick = onLoginSuccessClick,
        enabled = !isLoading,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = "Crear cuenta",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BottonAction(onRegisterClick: () -> Unit) {
    Text(
        text = "¿ Ya tienes una cuenta? Inicia Sesión",
        fontSize = 16.sp,
        color = Black,
        modifier = Modifier
            .height(32.dp)
            .clickable { onRegisterClick() }
    )
}