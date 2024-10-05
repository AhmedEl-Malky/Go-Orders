package com.example.go_orders.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.go_orders.composables.CategoriesLazyRow
import com.example.go_orders.composables.RestaurantCard
import com.example.go_orders.composables.RestaurantsSearchBar
import com.example.go_orders.composables.TopAppBar
import com.example.go_orders.state.ExploreRestaurantsScreenUIState
import com.example.go_orders.ui.theme.Beiruti

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreRestaurantsScreen() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
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
                            .padding(end = 6.dp),
                        text = "التصنيفات المشهورة",
                        fontFamily = Beiruti,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.End
                    )
                }
                item {
                    CategoriesLazyRow(ExploreRestaurantsScreenUIState().categories)
                }
                stickyHeader {
                    RestaurantsSearchBar()
                }
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 6.dp),
                        text = "المطاعم المتاحة في منطقتك",
                        fontFamily = Beiruti,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.End
                    )
                }
                item{
                    RestaurantCard()
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true,
    device = "spec:width=1080px,height=2400px,dpi=440"
)
@Composable
fun PreviewExploreRestaurantsScreen() {
    ExploreRestaurantsScreen()
}