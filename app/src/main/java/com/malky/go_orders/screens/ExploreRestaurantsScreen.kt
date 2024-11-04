package com.malky.go_orders.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.malky.go_orders.R
import com.malky.go_orders.composables.CategoriesLazyList
import com.malky.go_orders.composables.HorizontalBannersPager
import com.malky.go_orders.composables.LoadingAnimation
import com.malky.go_orders.composables.RestaurantCard
import com.malky.go_orders.composables.RestaurantsSearchBar
import com.malky.go_orders.composables.TopAppBar
import com.malky.go_orders.data.State
import com.malky.go_orders.state.ExploreRestaurantsUIState
import com.malky.go_orders.state.ExploreRestaurantsUIState.CategoryUIState
import com.malky.go_orders.state.HomeUIState
import com.malky.go_orders.ui.theme.Beiruti
import com.malky.go_orders.ui.theme.GoOrdersTheme


@Composable
fun ExploreRestaurantsScreen(
    state:ExploreRestaurantsUIState,
    homeState: HomeUIState,
    navController: NavController,
    filterOpenedRestaurants: (Boolean) -> Unit,
    searchForRestaurant: (String) -> Unit,
    showCityForm: () -> Unit,
    dismissCityForm: () -> Unit,
    onSelectCity: (HomeUIState.CityUIState) -> Unit,
    expandCitiesMenu: () -> Unit,
    collapseCitiesMenu: () -> Unit,
    onSelectCategory: (CategoryUIState) -> Unit,
) {

    when (state.screenState) {
        is State.Loading ->
            AnimatedVisibility(true) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                )
                {
                    AnimatedVisibility(visible = true) {
                        LoadingAnimation()
                    }
                }
            }


        is State.Success ->
            AnimatedVisibility(visible = true) {
                ExploreRestaurantsScreenContent(
                    state = state,
                    homeState = homeState,
                    filterOpenedRestaurants = filterOpenedRestaurants,
                    searchForRestaurant = searchForRestaurant,
                    showCityForm = showCityForm,
                    dismissCityForm = dismissCityForm,
                    onSelectCity = onSelectCity,
                    expandCitiesMenu = expandCitiesMenu,
                    collapseCitiesMenu = collapseCitiesMenu,
                    onSelectCategory = onSelectCategory,
                    navController = navController
                )
            }

        is State.Error ->
            AnimatedVisibility(true) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
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
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ExploreRestaurantsScreenContent(
    state: ExploreRestaurantsUIState,
    homeState: HomeUIState,
    filterOpenedRestaurants: (Boolean) -> Unit,
    searchForRestaurant: (String) -> Unit,
    showCityForm: () -> Unit,
    dismissCityForm: () -> Unit,
    onSelectCity: (HomeUIState.CityUIState) -> Unit,
    expandCitiesMenu: () -> Unit,
    collapseCitiesMenu: () -> Unit,
    onSelectCategory: (CategoryUIState) -> Unit,
    navController: NavController
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
                navController = navController,
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
                    HorizontalBannersPager(state.banners)
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
                    CategoriesLazyList(state.categories, onSelectCategory)
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
                when (state.restaurants) {
                    is State.Loading -> item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.background),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            LoadingAnimation()
                        }
                    }

                    is State.Success -> itemsIndexed(
                        state.restaurants.toData() ?: listOf()
                    ) { index, item ->
                        RestaurantCard(
                            restaurant = item,
                            restaurantCount = state.restaurants.toData()?.size ?: 0,
                            index = index,
                            navController = navController
                        )
                    }

                    is State.Error -> item {
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
        }

    }

}


@Preview(
    showSystemUi = true, showBackground = true,
    device = "spec:width=1080px,height=2400px,dpi=441",
    locale = "ar"
)
@Composable
private fun PreviewExploreRestaurantsScreen() {
    GoOrdersTheme {
        ExploreRestaurantsScreen(
            state = ExploreRestaurantsUIState(),
            homeState = HomeUIState(),
            navController = NavController(LocalContext.current),
            filterOpenedRestaurants = {},
            searchForRestaurant = {},
            showCityForm = {},
            dismissCityForm = {},
            onSelectCity = {},
            expandCitiesMenu = {},
            collapseCitiesMenu = {},
            onSelectCategory = {}
        )
    }
}