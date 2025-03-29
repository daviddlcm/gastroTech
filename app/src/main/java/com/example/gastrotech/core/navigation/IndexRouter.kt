package com.example.gastrotech.core.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.gastrotech.account.presentation.AccountScreen
import com.example.gastrotech.core.domain.constans.Routes
import com.example.gastrotech.home.presentation.HomeScreen
import com.example.gastrotech.home.presentation.HomeViewModel
import com.example.gastrotech.ui.navbar.NavBar

@Composable
fun IndexRouter(
    modifier: Modifier = Modifier,
    homeVM : HomeViewModel
){
    val route = rememberSaveable{
        mutableStateOf(Routes.HomeRoute.route)
    }

    Scaffold(
        bottomBar = {
            NavBar(route)
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            when(route.value){
                Routes.HomeRoute.route -> HomeScreen(homeViewModel = homeVM)
                Routes.AccountRoute.route -> AccountScreen()
            }
        }
    }
}