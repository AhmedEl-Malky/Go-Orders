package com.malky.go_orders.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.malky.go_orders.R
import com.malky.go_orders.navigations.Navigation
import com.malky.go_orders.state.ExploreRestaurantsUIState.RestaurantUIState
import com.malky.go_orders.ui.theme.Beiruti
import com.malky.go_orders.ui.theme.GoOrdersTheme

@Composable
fun RestaurantCard(
    restaurant: RestaurantUIState,
    restaurantCount: Int,
    index: Int,
    navController: NavController
) {
    RestaurantCardContent(
        restaurant,
        restaurantCount,
        index,
        navController
    )
}

@Composable
private fun RestaurantCardContent(
    restaurant: RestaurantUIState,
    restaurantCount: Int,
    index: Int,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clip(
                when (index) {
                    0 -> RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)
                    restaurantCount - 1 -> RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp)
                    else -> RoundedCornerShape(0.dp)
                }
            )
            .background(MaterialTheme.colorScheme.secondary)
            .padding(
                if (index == restaurantCount - 1) PaddingValues(
                    top = 12.dp,
                    start = 12.dp,
                    end = 12.dp,
                    bottom = 14.dp
                ) else PaddingValues(
                    top = 14.dp,
                    start = 12.dp,
                    end = 12.dp
                )
            ),
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onBackground),
        border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.BottomStart
            ) {
                AsyncImage(
                    modifier = Modifier
                        .height(230.dp)
                        .clip(RoundedCornerShape(topEnd = 6.dp, topStart = 6.dp)),
                    model = restaurant.cover,
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
                    model = restaurant.logo,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Restaurant logo",
                    alpha = 0.95f,
                    placeholder = painterResource(R.drawable.logo_placeholder)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomEnd = 6.dp, bottomStart = 6.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(24.dp)
                            .padding(horizontal = 4.dp),
                        painter = painterResource(R.drawable.verified),
                        contentDescription = "Verified"
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = restaurant.name,
                        fontFamily = Beiruti,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        modifier = Modifier
                            .padding(
                                vertical = 2.dp,
                                horizontal = 4.dp
                            )
                            .clip(RoundedCornerShape(100))
                            .background(
                                if (restaurant.openNow)
                                    MaterialTheme.colorScheme.surfaceContainerLow
                                else
                                    MaterialTheme.colorScheme.error.copy(alpha = 0.33f)
                            )
                            .padding(
                                vertical = 2.dp,
                                horizontal = 8.dp
                            ),

                        text = if (restaurant.openNow) "مفتوح الآن" else "مغلق الآن",
                        fontSize = 14.sp,
                        fontFamily = Beiruti,
                        color = if (restaurant.openNow)
                            MaterialTheme.colorScheme.surfaceContainerHigh
                        else
                            MaterialTheme.colorScheme.error
                    )
                }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = restaurant.description,
                    fontFamily = Beiruti,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    color = MaterialTheme.colorScheme.onTertiary,
                    textAlign = TextAlign.Start,
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 6.dp),
                    onClick = {navController.navigate(Navigation.RestaurantScreen(restaurant.id))},
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.2f
                        )
                    ),
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 12.dp,
                        top = 12.dp,
                        bottom = 12.dp
                    )
                ) {
                    Text(
                        text = "تصفح الMenu",
                        fontFamily = Beiruti,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        modifier = Modifier
                            .size(20.dp),
                        painter = painterResource(R.drawable.menu),
                        contentDescription = "Check Menu",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview(locale = "ar")
@Composable
fun PreviewRestaurantCard() {
    GoOrdersTheme {
        RestaurantCard(
            restaurant = RestaurantUIState(openNow = false),
            restaurantCount = 4,
            index = 0,
            navController = rememberNavController()
        )
    }
}