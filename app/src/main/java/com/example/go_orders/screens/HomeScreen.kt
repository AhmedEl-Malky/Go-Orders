package com.example.go_orders.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.go_orders.R
import com.example.go_orders.composables.TopAppBar
import com.example.go_orders.navigations.Navigation
import com.example.go_orders.ui.theme.Beiruti
import com.example.go_orders.ui.theme.GoOrdersTheme

@Composable
fun HomeScreen(
    navController: NavController
) {
    HomeScreenContent(
        goExploreRestaurants = { navController.navigate(Navigation.ExploreRestaurantsScreen.route) }
    )
}

@Composable
private fun HomeScreenContent(
    goExploreRestaurants: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            TopAppBar()
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
                                onClick = {},
                                shape = RoundedCornerShape(6.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                            ) {
                                Icon(
                                    modifier = Modifier.padding(8.dp),
                                    painter = painterResource(R.drawable.map),
                                    contentDescription = "Location",
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                                Text(
                                    modifier = Modifier.padding(end = 8.dp),
                                    text = "اختر مدينتك",
                                    fontFamily = Beiruti,
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Button(
                                onClick = goExploreRestaurants,
                                shape = RoundedCornerShape(6.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer)
                            ) {

                                Text(
                                    modifier = Modifier.padding(end = 12.dp),
                                    text = "تصفح المطاعم",
                                    fontFamily = Beiruti,
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Icon(
                                    modifier = Modifier.padding(
                                        top = 6.dp,
                                        bottom = 6.dp,
                                        start = 8.dp,
                                        end = 12.dp,
                                    ),
                                    painter = painterResource(R.drawable.restaurant),
                                    contentDescription = "Location",
                                    tint = MaterialTheme.colorScheme.primary
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true, showSystemUi = true,
    device = "spec:width=1080px,height=2400px,dpi=440", locale = "ar"
)
@Composable
private fun PreviewHomeScreen() {
    GoOrdersTheme {
        HomeScreenContent(goExploreRestaurants = {})
    }
}