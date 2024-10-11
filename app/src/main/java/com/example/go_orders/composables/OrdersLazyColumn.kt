package com.example.go_orders.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.go_orders.ui.theme.GoOrdersTheme

@Composable
fun OrdersLazyColumn(){
    OrdersLazyColumnContent(list)
}

@Composable
fun OrdersLazyColumnContent(
    ordersList: List<Int>
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 24.dp, horizontal = 8.dp)
    ) {
        items(ordersList) {
            OrderCard()
        }
    }
}

@Preview(locale = "ar")
@Composable
fun PreviewOrdersLazyColumn(){
    GoOrdersTheme {
        OrdersLazyColumn()
    }
}


val list = listOf(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)