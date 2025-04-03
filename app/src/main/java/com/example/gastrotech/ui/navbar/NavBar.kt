package com.example.gastrotech.ui.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gastrotech.core.domain.constans.Routes
import com.example.gastrotech.ui.navbar.composable.itembar.ItemBar

@Composable
fun NavBar(route: MutableState<String>, isLoggedIn: Boolean) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        for (page in Routes.pages) {
            if (page.route == Routes.OrdersRoute.route) {
                if (isLoggedIn) {
                    ItemBar(
                        page,
                        selected = route.value == page.route,
                        modifier = Modifier.clickable {
                            route.value = page.route
                        }
                    )
                }
            } else {
                ItemBar(
                    page,
                    selected = route.value == page.route,
                    modifier = Modifier.clickable {
                        route.value = page.route
                    }
                )
            }
        }
    }
}
