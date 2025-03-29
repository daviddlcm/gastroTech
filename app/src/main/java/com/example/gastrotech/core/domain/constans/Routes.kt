package com.example.gastrotech.core.domain.constans

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import com.example.gastrotech.core.domain.models.NavItem

object Routes {

    val HomeRoute = NavItem("Home","home",Icons.Outlined.Home)
    val AccountRoute = NavItem("Account","account",Icons.Outlined.AccountCircle)

    val pages = listOf<NavItem>(HomeRoute, AccountRoute)
}