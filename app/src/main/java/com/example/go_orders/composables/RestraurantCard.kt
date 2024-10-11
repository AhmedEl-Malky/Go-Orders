package com.example.go_orders.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import coil3.compose.AsyncImage
import com.example.go_orders.R
import com.example.go_orders.state.ExploreRestaurantsScreenUIState.RestaurantUIState
import com.example.go_orders.ui.theme.Beiruti
import com.example.go_orders.ui.theme.GoOrdersTheme

@Composable
fun RestaurantCard(
    restaurant: RestaurantUIState,
    restaurantCount: Int,
    index: Int
) {
    RestaurantCardContent(
        restaurant,
        restaurantCount,
        index
    )
}

@Composable
private fun RestaurantCardContent(
    restaurant: RestaurantUIState,
    restaurantCount: Int,
    index: Int
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
                    contentDescription = "Restaurant banner",
                )
                AsyncImage(
                    modifier = Modifier
                        .padding(start = 12.dp, bottom = 12.dp)
                        .size(72.dp)
                        .clip(RoundedCornerShape(topEnd = 18.dp))
                        .border(width = 0.5.dp, color = MaterialTheme.colorScheme.primary),
                    model = restaurant.logo,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Restaurant logo",
                    alpha = 0.95f,
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
                                horizontal = 8.dp
                            )
                            .clip(RoundedCornerShape(100))
                            .background(MaterialTheme.colorScheme.surfaceContainerLow)
                            .padding(
                                vertical = 2.dp,
                                horizontal = 10.dp
                            ),

                        text = "",
                        fontSize = 14.sp,
                        fontFamily = Beiruti,
                        color = MaterialTheme.colorScheme.surfaceContainerHigh
                    )
                }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = restaurant.description,
                    fontFamily = Beiruti,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onTertiary,
                    textAlign = TextAlign.Start,
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 6.dp),
                    onClick = {},
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.2f
                        )
                    )
                ) {
                    Text(
                        text = "تصفح الMenu",
                        fontFamily = Beiruti,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .padding(start = 4.dp),
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
//    RestaurantCard()
    }
}