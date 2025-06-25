package com.example.goorders.restaurant.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.goorders.R
import com.example.goorders.core.presentation.components.TopAppBar
import com.example.goorders.core.presentation.theme.Beiruti
import com.example.goorders.core.presentation.theme.GoOrdersTheme
import com.example.goorders.main.presentation.MainActions
import com.example.goorders.main.presentation.MainState
import com.example.goorders.restaurant.domain.MenuOptions
import com.example.goorders.restaurant.presentation.components.MenuImagesPager
import com.example.goorders.restaurant.presentation.components.MenuItemCard
import com.example.goorders.restaurant.presentation.components.MenuOptionsBar


@Composable
fun RestaurantScreen(
    restaurantID: Int,
    state: RestaurantState,
    mainState: MainState,
    onAction: (RestaurantActions) -> Unit,
    onMainAction: (MainActions) -> Unit
) {
    LaunchedEffect(Unit) {
        onAction(RestaurantActions.GetRestaurantInfo(restaurantID))
        onAction(RestaurantActions.GetMenuCategories(restaurantID))
        onAction(RestaurantActions.GetMenuItems(restaurantID))
    }
    RestaurantScreenContent(
        state = state,
        mainState = mainState,
        onAction = onAction,
        onMainAction = onMainAction
    )
//    when (state.restaurant) {
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
//        is State.Success ->
//            AnimatedVisibility(visible = true) {
//                RestaurantScreenContent(
//                    state = state,
//                    homeState = homeState,
//                    navController = navController,
//                    onEvent = onAction,
//                    onHomeEvent = onMainAction
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
private fun RestaurantScreenContent(
    state: RestaurantState,
    mainState: MainState,
    onAction: (RestaurantActions) -> Unit,
    onMainAction: (MainActions) -> Unit
) {
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
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 6.dp, horizontal = 12.dp)
            ) {
                item {
                    Box(
                        contentAlignment = Alignment.BottomStart
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .height(230.dp)
                                .clip(RoundedCornerShape(topEnd = 6.dp, topStart = 6.dp)),
                            model = state.restaurant?.cover ?: "",
                            contentScale = ContentScale.Crop,
                            contentDescription = "Restaurant Cover",
                            placeholder = painterResource(R.drawable.cover_placeholder)
                        )
                        AsyncImage(
                            modifier = Modifier
                                .padding(start = 12.dp, bottom = 12.dp)
                                .size(72.dp)
                                .clip(RoundedCornerShape(topEnd = 18.dp))
                                .border(
                                    width = 0.5.dp,
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(topEnd = 18.dp)
                                ),
                            model = state.restaurant?.logo ?: "",
                            contentScale = ContentScale.Crop,
                            contentDescription = "Restaurant logo",
                            alpha = 0.95f,
                            placeholder = painterResource(R.drawable.logo_placeholder)
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .size(32.dp)
                                .padding(end = 6.dp),
                            painter = painterResource(R.drawable.verified),
                            contentDescription = "Verified"
                        )
                        Text(
                            modifier = Modifier.weight(1f),
                            text = state.restaurant?.name ?: "",
                            fontFamily = Beiruti,
                            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onBackground,
                            maxLines = 2,
                            lineHeight = 30.sp,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Start
                        )
                        Button(
                            contentPadding = PaddingValues(
                                horizontal = 8.dp,
                                vertical = 8.dp
                            ),
                            onClick = {},
                            shape = RoundedCornerShape(6.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF22C55E)
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "Contact",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                modifier = Modifier,
                                text = "أرقام التواصل",
                                fontFamily = Beiruti,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        text = state.restaurant?.description ?: "",
                        fontFamily = Beiruti,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = MaterialTheme.colorScheme.onTertiary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start,
                    )
                }
                item {
                    MenuOptionsBar(
                        option = state.menuOption,
                        onAction = onAction,
                        restaurantID = state.restaurant?.id ?: 0
                    )
                }
                when (state.menuOption) {
                    MenuOptions.ONLINE_ORDERS -> {
                        stickyHeader {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                                    .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f))
                                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                                text = "أقسام المنيو",
                                fontFamily = Beiruti,
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onBackground,
                                textAlign = TextAlign.Start
                            )
                        }
                        itemsIndexed(state.menuCategories) { index, item ->
                            MenuItemCard(
                                category = item,
                                menuSize = state.menuCategories.size,
                                index = index,
                                menuItems = state.menuItems,
                                restaurantLogo = state.restaurant?.logo ?: ""
                            )
                        }

                    }

                    MenuOptions.MENU_IMAGES -> item { MenuImagesPager(state.menuImages) }
                    MenuOptions.RESTAURANT_INFO -> {}

                }

            }
        }

    }
}


@Preview(
    showBackground = true, showSystemUi = true,
    device = "spec:width=1080px,height=2400px,dpi=440",
    locale = "ar"
)
@Composable
private fun PreviewRestaurantScreen() {
    GoOrdersTheme {
        RestaurantScreen(
            restaurantID = 1,
            state = RestaurantState(),
            mainState = MainState(),
            onAction = {},
            onMainAction = {}
        )
    }
}
