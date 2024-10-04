package com.example.go_orders.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.go_orders.composables.CategoriesLazyRow
import com.example.go_orders.composables.TopAppBar
import com.example.go_orders.state.ExploreRestaurantsScreenUIState

@Composable
fun ExploreRestaurantsScreen(){
    Column(
        modifier = Modifier.background(Color(0xFF292524))
    ) {
        TopAppBar()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                CategoriesLazyRow(ExploreRestaurantsScreenUIState().categories)
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewExploreRestaurantsScreen(){
    ExploreRestaurantsScreen()
}