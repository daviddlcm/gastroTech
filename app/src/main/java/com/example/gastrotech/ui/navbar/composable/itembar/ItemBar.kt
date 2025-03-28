package com.example.gastrotech.ui.navbar.composable.itembar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gastrotech.core.domain.models.NavItem

@Composable
fun ItemBar(
    page: NavItem,
    selected: Boolean,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 12.dp)
    ) {
        Image(
            imageVector = page.icon,
            contentDescription = page.title,
            colorFilter = ColorFilter.tint(
                if(selected) MaterialTheme.colorScheme.inversePrimary
                else MaterialTheme.colorScheme.onPrimary
            ),
            modifier = modifier.padding(bottom = 8.dp)
                .size(24.dp)
        )
        Text(
            page.title,
            fontSize = 12.sp,
            color = if(selected) MaterialTheme.colorScheme.inversePrimary
            else MaterialTheme.colorScheme.onPrimary
        )
    }
}