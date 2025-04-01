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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Composable
fun RegisterScreen(onNavigate: () -> Unit) {

    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var contraseña by  remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var teléfono by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }

    var isButtonEnabled = nombre.isNotBlank() && apellidos.isNotBlank() && contraseña.isNotBlank() && correo.isNotBlank() && teléfono.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TitleText()
        Spacer(modifier = Modifier.height(16.dp))
        NombreField(
            nombre = nombre,
            onNombreChange = { nombre = it}
        )
        Spacer(modifier = Modifier.height(8.dp))
        ApellidosField(
            apellidos = apellidos,
            onApellidosChange = { apellidos = it}
        )
        Spacer(modifier = Modifier.height(16.dp))
        CorreoField(
            correo = correo,
            onCorreoChange = { correo = it}
        )
        Spacer(modifier = Modifier.height(16.dp))
        ContraseñaField(
            contraseña = contraseña,
            onContraseñahange = { contraseña = it},
            isVisible = isVisible,
            onVisibilityChange = { isVisible = !isVisible}
        )
        Spacer(modifier = Modifier.height(16.dp))
        TelefonoField(
            teléfono = teléfono,
            onTeléfonoChange = { teléfono = it}
        )
        Spacer(modifier = Modifier.height(16.dp))
        LoginButton(isButtonEnabled,  onLoginSuccessClick = {

        })
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
    isButtonEnabled: Boolean,
    onLoginSuccessClick: () -> Unit
) {
    Button(
        onClick = onLoginSuccessClick,
        enabled = isButtonEnabled,
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