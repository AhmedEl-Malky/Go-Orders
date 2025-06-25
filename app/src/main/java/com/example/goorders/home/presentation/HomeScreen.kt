package com.example.goorders.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goorders.core.presentation.components.CityForm
import com.example.goorders.core.presentation.components.TopAppBar
import com.example.goorders.core.presentation.theme.Beiruti
import com.example.goorders.core.presentation.theme.GoOrdersTheme
import com.example.goorders.home.presentation.components.CategoriesLazyList
import com.example.goorders.home.presentation.components.HorizontalBannersPager
import com.example.goorders.home.presentation.components.RestaurantCard
import com.example.goorders.home.presentation.components.SearchBar
import com.example.goorders.main.presentation.MainActions
import com.example.goorders.main.presentation.MainState

@Composable
fun HomeScreen(
    state: HomeState,
    mainState: MainState,
    onAction: (HomeActions) -> Unit,
    onMainAction: (MainActions) -> Unit,
    goToRestaurant: (Int) -> Unit
) {
    LaunchedEffect(Unit) {
        onAction(HomeActions.GetCategories)
        onAction(HomeActions.GetRestaurants(mainState.currentCity!!.id))
    }
    HomeScreenContent(
        state = state,
        mainState = mainState,
        onAction = onAction,
        onMainAction = onMainAction,
        goToRestaurant = goToRestaurant
    )
//    when (state.screenState) {
//        is State.Loading ->
//            AnimatedVisibility(true) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(MaterialTheme.colorScheme.background),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                )
//                {
//                    AnimatedVisibility(visible = true) {
//                        LoadingAnimation()
//                    }
//                }
//            }
//
//
//        is State.Success ->
//            AnimatedVisibility(visible = true) {
//                HomeScreen(
//                    state = state,
//                    homeState = mainState,
//                    navController = navController,
//                    onEvent = onEvent,
//                    onHomeEvent = onHomeEvent,
//                )
//            }
//
//        is State.Error ->
//            AnimatedVisibility(true) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(MaterialTheme.colorScheme.background),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    Image(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(24.dp),
//                        painter = painterResource(R.drawable.error),
//                        contentDescription = "Error"
//                    )
//                }
//            }
//    }
}

@Composable
private fun HomeScreenContent(
    state: HomeState,
    mainState: MainState,
    onAction: (HomeActions) -> Unit,
    onMainAction: (MainActions) -> Unit,
    goToRestaurant: (Int) -> Unit
) {
    if(mainState.isCityFormVisible)
        CityForm(
            state = mainState,
            onAction = onMainAction
        )
    Scaffold(
        topBar = {
            TopAppBar(
                state = mainState,
                navigateToCart = {},
                onAction = onMainAction
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                    CategoriesLazyList(
                        categories = state.categories,
                        selectedCategory = state.selectedCategory,
                        onCategorySelect = {
                            onAction(HomeActions.OnCategorySelect(it))
                        }
                    )
                }
                stickyHeader {
                    SearchBar(
                        isOpen = state.isOpenFilter,
                        searchQuery = state.searchQuery,
                        onSearchChange = {
                            onAction(HomeActions.OnRestaurantsSearchChange(it))
                        },
                        onFilterOpenRestaurant = {
                            onAction(HomeActions.OnOpenRestaurantsFilter)
                        }
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
                itemsIndexed(state.filteredRestaurants) { index, restaurant ->
                    RestaurantCard(
                        restaurant = restaurant,
                        restaurantCount = state.filteredRestaurants.size,
                        index = index,
                        goToRestaurant = goToRestaurant
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
private fun PreviewExploreRestaurantsScreen() {
    GoOrdersTheme {
        HomeScreen(
            state = HomeState(),
            mainState = MainState(),
            onAction = {},
            onMainAction = {},
            goToRestaurant = {}
        )
    }
}