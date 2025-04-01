package com.example.gastrotech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.gastrotech.core.navigation.IndexRouter
import com.example.gastrotech.home.presentation.viewModel.HomeViewModel
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

