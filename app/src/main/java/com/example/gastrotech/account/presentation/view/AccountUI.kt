package com.example.gastrotech.account.presentation.view

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
fun AccountScreen(onNavigate: () -> Unit, onNavigateHome: () -> Unit, isLoggedIn: Boolean, onLoginChange: (Boolean) -> Unit) {
    var correo by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    if (isLoggedIn){
        LoggedInView(
            //correo = correo,
            onLogout = {
                onLoginChange(false)
            }
        )
    } else {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            TitleText()
            Spacer(modifier = Modifier.height(16.dp))
            EmailField(
                correo = correo,
                onCorreoChange = { contraseña = it}
            )
            Spacer(modifier = Modifier.height(5.dp))
            PasswordField(
                contraseña = contraseña,
                onContraseñaChange = { contraseña = it},
                isPasswordVisible = isPasswordVisible,
                onPasswordVisibilityChange = {isPasswordVisible = !isPasswordVisible}
            )
            Spacer(modifier = Modifier.height(24.dp))
            LoginButton(
                onLoginSuccessClick = {
                    onLoginChange(true)
                    onNavigateHome()
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            RegisterLink(onRegisterClick = {onNavigate()})
        }
    }
}


@Composable
fun TitleText() {
    Text(
        text = "Iniciar Sesión",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}

@Composable
fun EmailField(
    correo: String,
    onCorreoChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Correo Electrónico", color = Color.Black)
        OutlinedTextField(
            value = correo,
            onValueChange = onCorreoChange,
            label = { Text("Correo") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray
            ),
            shape = RoundedCornerShape(18.dp),
            placeholder = { Text("example@gmail.com", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "E-mail", tint = Color.Black) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun PasswordField(
    contraseña: String,
    isPasswordVisible: Boolean,
    onContraseñaChange: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Contraseña", color = Color.Black)
        OutlinedTextField(
            value = contraseña,
            onValueChange = onContraseñaChange,
            label = { Text("Contraseña") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray
            ),
            shape = RoundedCornerShape(15.dp),
            placeholder = { Text("********", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Lock Icon", tint = Color.Black) },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                IconButton(onClick = onPasswordVisibilityChange) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle Password Visibility",
                        tint = Color.Black
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun LoginButton(onLoginSuccessClick: () -> Unit) {
    Button(
        onClick = onLoginSuccessClick,
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
            text = "Iniciar Sesión",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun RegisterLink(onRegisterClick: () -> Unit) {
    Text(
        text = "No tiene una cuenta ¿Crear cuenta?",
        fontSize = 16.sp,
        color = Black,
        modifier = Modifier
            .height(32.dp)
            .clickable { onRegisterClick() }
    )
}

@Composable
fun LoggedInView(onLogout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bienvenido",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onLogout,
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
                text = "Cerrar Sesión",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
