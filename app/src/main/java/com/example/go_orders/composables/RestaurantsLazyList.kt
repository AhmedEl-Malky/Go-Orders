package com.example.go_orders.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RestaurantLazyList(){
    RestaurantLazyListContent()
}

@Composable
private fun RestaurantLazyListContent() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        items(list){
            RestaurantCard()
        }
    }
}

val list = listOf(1,2,3,4,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,)
@Preview
@Composable
fun PreviewRestaurantLazyList(){
    RestaurantLazyList()
}