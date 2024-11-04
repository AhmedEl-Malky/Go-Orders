package com.malky.go_orders

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.malky.go_orders.navigations.NavigationGraph
import com.malky.go_orders.ui.theme.GoOrdersTheme

@Composable
fun GoOrdersApp() {
    GoOrdersTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val navController = rememberNavController()
            NavigationGraph(navController)
        }
    }
}