package com.example.go_orders.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.go_orders.composables.CategoriesLazyList
import com.example.go_orders.composables.RestaurantCard
import com.example.go_orders.composables.RestaurantsSearchBar
import com.example.go_orders.composables.TopAppBar
import com.example.go_orders.state.ExploreRestaurantsScreenUIState
import com.example.go_orders.ui.theme.Beiruti
import com.example.go_orders.viewmodels.ExploreRestaurantsViewModel


@Composable
fun ExploreRestaurantsScreen(
//    viewModel: ExploreRestaurantsViewModel = hiltViewModel()
) {
    val viewModel by remember { mutableStateOf(ExploreRestaurantsViewModel()) }
    val state by viewModel.state.collectAsState()
    ExploreRestaurantsScreenContent(
        state,
        viewModel::filterOpenedRestaurants,
        viewModel::searchForRestaurant
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreRestaurantsScreenContent(
    state: ExploreRestaurantsScreenUIState,
    filterOpenedRestaurants: (Boolean) -> Unit,
    searchForRestaurant: (String) -> Unit
) {
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
                    CategoriesLazyList(state.categories)
                }
                stickyHeader {
                    RestaurantsSearchBar(
                        state.isOpenFilter,
                        state.searchInput,
                        filterOpenedRestaurants,
                        searchForRestaurant
                    )
                }
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        text = "المطاعم المتاحة في منطقتك",
                        fontFamily = Beiruti,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.End
                    )
                }
                itemsIndexed(state.restaurants) { index, item ->
                    RestaurantCard(
                        restaurant = item,
                        restaurantCount = state.restaurants.size,
                        index = index
                    )
                }
            }
        }
    }
}

@Preview(
    showSystemUi = true, showBackground = true,
    device = "spec:width=1080px,height=2400px,dpi=441"
)
@Composable
fun PreviewExploreRestaurantsScreen() {
    ExploreRestaurantsScreen()
}