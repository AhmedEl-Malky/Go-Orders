package com.example.go_orders.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.go_orders.composables.CategoriesLazyRow
import com.example.go_orders.composables.RestaurantsSearchBar
import com.example.go_orders.composables.TopAppBar
import com.example.go_orders.state.ExploreRestaurantsScreenUIState
import com.example.go_orders.ui.theme.Beiruti

@OptIn(ExperimentalFoundationApi::class)
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
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 12.dp),
                    text = "التصنيفات المشهورة",
                    fontFamily = Beiruti,
                    color = Color.White,
                    fontSize = 22.sp,
                    textAlign = TextAlign.End
                )
            }
            item {
                CategoriesLazyRow(ExploreRestaurantsScreenUIState().categories)
            }
            stickyHeader {
                RestaurantsSearchBar()
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewExploreRestaurantsScreen(){
    ExploreRestaurantsScreen()
}