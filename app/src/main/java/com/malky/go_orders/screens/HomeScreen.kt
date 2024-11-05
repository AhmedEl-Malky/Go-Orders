package com.malky.go_orders.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.malky.go_orders.R
import com.malky.go_orders.composables.CityForm
import com.malky.go_orders.composables.LoadingAnimation
import com.malky.go_orders.composables.TopAppBar
import com.malky.go_orders.data.State
import com.malky.go_orders.navigations.Navigation
import com.malky.go_orders.screens.events.HomeEvents
import com.malky.go_orders.state.HomeUIState
import com.malky.go_orders.ui.theme.Beiruti
import com.malky.go_orders.ui.theme.GoOrdersTheme

@Composable
fun HomeScreen(
    state: HomeUIState,
    navController: NavController,
    onEvent: (HomeEvents) -> Unit
) {
    when (state.availableCities) {
        is State.Loading ->
            AnimatedVisibility(true) {
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


        is State.Success ->
            AnimatedVisibility(true) {
                HomeScreenContent(
                    navController = navController,
                    state = state,
                    onEvent = onEvent
                )
            }


        is State.Error ->
            AnimatedVisibility(true) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
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

@Composable
private fun HomeScreenContent(
    navController: NavController,
    state: HomeUIState,
    onEvent: (HomeEvents) -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TopAppBar(
                state = state,
                onEvent = onEvent,
                navController = navController
            )
            Box(
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                    .verticalScroll(rememberScrollState()),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(6.dp)),
                    painter = painterResource(R.drawable.hero),
                    contentDescription = "Hero pic",
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier.padding(top = 100.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "أطلب يوصلك أسرع Order 👌",
                            fontFamily = Beiruti,
                            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.background
                        )
                        Column(
                            verticalArrangement = Arrangement.spacedBy(2.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "مع Go Orders كل",
                                fontFamily = Beiruti,
                                fontSize = MaterialTheme.typography.displayMedium.fontSize,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "طلباتك في مكان واحد",
                                fontFamily = Beiruti,
                                fontSize = MaterialTheme.typography.displayMedium.fontSize,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        Column(
                            modifier = Modifier.padding(top = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                onClick = { onEvent(HomeEvents.ShowCityForm) },
                                shape = RoundedCornerShape(6.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer
                                ),
                                contentPadding = PaddingValues(
                                    start = 12.dp,
                                    end = 16.dp,
                                    top = 12.dp,
                                    bottom = 12.dp
                                )
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.map),
                                    contentDescription = "Location",
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "اختر مدينتك",
                                    fontFamily = Beiruti,
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Button(
                                contentPadding = PaddingValues(
                                    start = 16.dp,
                                    end = 12.dp,
                                    top = 12.dp,
                                    bottom = 12.dp
                                ),
                                onClick = { navController.navigate(Navigation.ExploreRestaurantsScreen) },
                                shape = RoundedCornerShape(6.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            ) {
                                Text(
                                    modifier = Modifier.padding(end = 12.dp),
                                    text = "تصفح المطاعم",
                                    fontFamily = Beiruti,
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    painter = painterResource(R.drawable.restaurant),
                                    contentDescription = "Location",
                                    tint = MaterialTheme.colorScheme.primary
                                )

                            }
                        }
                    }
                }
            }
            if (state.isCityFormShown) {
                CityForm(
                    state = state,
                    onEvent = onEvent
                )
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
private fun PreviewHomeScreen() {
    GoOrdersTheme {
        HomeScreen(
            state = HomeUIState(availableCities = State.Success(emptyList())),
            navController = NavController(LocalContext.current),
            onEvent = {}
        )
    }
}