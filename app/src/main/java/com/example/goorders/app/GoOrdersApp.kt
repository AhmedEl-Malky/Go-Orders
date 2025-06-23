package com.example.goorders.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.goorders.core.presentation.theme.GoOrdersTheme

@Composable
fun GoOrdersApp() {
    GoOrdersTheme {
        val navController = rememberNavController()
//        NavigationGraph(navController)
    }
}