package com.example.go_orders.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.go_orders.R
import com.example.go_orders.composables.CategoriesLazyList
import com.example.go_orders.composables.HorizontalBannersPager
import com.example.go_orders.composables.LoadingAnimation
import com.example.go_orders.composables.RestaurantCard
import com.example.go_orders.composables.RestaurantsSearchBar
import com.example.go_orders.composables.TopAppBar
import com.example.go_orders.data.State
import com.example.go_orders.state.ExploreRestaurantsScreenUIState
import com.example.go_orders.state.HomeScreenUIState
import com.example.go_orders.ui.theme.Beiruti
import com.example.go_orders.ui.theme.GoOrdersTheme
import com.example.go_orders.viewmodels.ExploreRestaurantsViewModel
import com.example.go_orders.viewmodels.HomeViewModel


@Composable
fun ExploreRestaurantsScreen(
    viewModel: ExploreRestaurantsViewModel,
    homeViewModel: HomeViewModel
) {
    val state by viewModel.state.collectAsState()
    val homeState by homeViewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getCategories()
        viewModel.getAllRestaurants()
    }
    when (state.restaurants) {
        is State.Loading ->
            Column(
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            { LoadingAnimation() }
        is State.Success ->
            ExploreRestaurantsScreenContent(
            state = state,
            homeState = homeState,
            filterOpenedRestaurants = viewModel::filterOpenedRestaurants,
            searchForRestaurant = viewModel::searchForRestaurant,
            showCityForm = homeViewModel::showCityForm,
            dismissCityForm = homeViewModel::dismissCityForm,
            onSelectCity = homeViewModel::onSelectCity,
            expandCitiesMenu = homeViewModel::expandCitiesMenu,
            collapseCitiesMenu = homeViewModel::collapseCitiesMenu
        )
        is State.Error ->
            Column(
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    painter = painterResource(R.drawable.error),
                    contentDescription = "Error"
                )
            }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreRestaurantsScreenContent(
    state: ExploreRestaurantsScreenUIState,
    homeState: HomeScreenUIState,
    filterOpenedRestaurants: (Boolean) -> Unit,
    searchForRestaurant: (String) -> Unit,
    showCityForm: () -> Unit,
    dismissCityForm: () -> Unit,
    onSelectCity: (HomeScreenUIState.CityUIState) -> Unit,
    expandCitiesMenu: () -> Unit,
    collapseCitiesMenu: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TopAppBar(
                state = homeState,
                showCityForm = showCityForm,
                dismissCityForm = dismissCityForm,
                onSelectCity = onSelectCity,
                expandCitiesMenu = expandCitiesMenu,
                collapseCitiesMenu = collapseCitiesMenu
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    HorizontalBannersPager()
                }
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 6.dp),
                        text = "التصنيفات المشهورة",
                        fontFamily = Beiruti,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start
                    )
                }
                item {
                    CategoriesLazyList(state.categories.toData() ?: emptyList())
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
                        textAlign = TextAlign.Start
                    )
                }
                itemsIndexed(state.restaurants.toData() ?: listOf()) { index, item ->
                    RestaurantCard(
                        restaurant = item,
                        restaurantCount = state.restaurants.toData()?.size ?: 0,
                        index = index
                    )
                }
            }
        }

    }

}


@Preview(
    showSystemUi = true, showBackground = true,
    device = "spec:width=1080px,height=2400px,dpi=441",
    locale = "ar"
)
@Composable
fun PreviewExploreRestaurantsScreen() {
    GoOrdersTheme {
//        ExploreRestaurantsScreen()
    }
}