package com.example.gastrotech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.gastrotech.core.navigation.IndexRouter
import com.example.gastrotech.home.presentation.HomeScreen
import com.example.gastrotech.home.presentation.HomeViewModel
import com.example.gastrotech.ui.theme.GastroTechTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val homeVM = ViewModelProvider(this).get(HomeViewModel::class.java)
        setContent {
            GastroTechTheme {
                IndexRouter(
                    homeVM = homeVM
                )
            }
        }
    }
}

