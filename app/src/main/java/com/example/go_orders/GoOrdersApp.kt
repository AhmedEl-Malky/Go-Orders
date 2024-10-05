package com.example.go_orders

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.go_orders.screens.ExploreRestaurantsScreen
import com.example.go_orders.screens.HomeScreen
import com.example.go_orders.ui.theme.GoOrdersTheme

@Composable
fun GoOrdersApp(){
    GoOrdersTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//            HomeScreen()
            ExploreRestaurantsScreen()
        }
    }
}