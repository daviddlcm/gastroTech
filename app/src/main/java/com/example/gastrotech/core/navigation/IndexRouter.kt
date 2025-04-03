package com.example.gastrotech.core.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.gastrotech.account.presentation.view.AccountScreen
import com.example.gastrotech.account.presentation.viewModel.LoginViewModel
import com.example.gastrotech.carts.presentation.view.CartScreen
import com.example.gastrotech.carts.presentation.viewModel.CartViewModel
import com.example.gastrotech.confirmOrders.presentation.viewModel.ConfirmOrdersViewModel
import com.example.gastrotech.core.domain.constans.Routes
import com.example.gastrotech.home.presentation.view.HomeScreen
import com.example.gastrotech.home.presentation.viewModel.HomeViewModel
import com.example.gastrotech.orders.presentation.view.OrdersScreen
import com.example.gastrotech.orders.presentation.viewModel.PedidosViewModel
import com.example.gastrotech.register.presentation.view.RegisterScreen
import com.example.gastrotech.register.presentation.viewModel.RegisterViewModel
import com.example.gastrotech.ui.navbar.NavBar

@Composable
fun IndexRouter(
    modifier: Modifier = Modifier,
    homeVM : HomeViewModel
){
    val route = rememberSaveable{ mutableStateOf(Routes.HomeRoute.route) }
    val isLoggedIn = rememberSaveable {  mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            if (route.value != Routes.RegisterRoute.route) {
                NavBar(route, isLoggedIn.value)
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            when(route.value){
                Routes.HomeRoute.route -> HomeScreen(
                    homeViewModel = homeVM,
                    confirmOrdersViewModel = ConfirmOrdersViewModel()
                )
                Routes.AccountRoute.route -> AccountScreen(
                    loginViewModel = LoginViewModel(),
                    onNavigate = { route.value = Routes.RegisterRoute.route},
                    onNavigateHome = {
                        isLoggedIn.value = true
                        route.value = Routes.HomeRoute.route
                    },
                    isLoggedIn = isLoggedIn.value,
                    onLoginChange = { isLoggedIn.value = it }
                )
                //Routes.CartRoute.route -> if (isLoggedIn.value) CartScreen()
                Routes.OrdersRoute.route -> if (isLoggedIn.value) OrdersScreen(
                    pedidosViewModel = PedidosViewModel()
                )
                Routes.RegisterRoute.route -> RegisterScreen(
                    registerViewModel = RegisterViewModel(),
                    onNavigate = { route.value = Routes.AccountRoute.route}
                )
            }
        }

    }
}