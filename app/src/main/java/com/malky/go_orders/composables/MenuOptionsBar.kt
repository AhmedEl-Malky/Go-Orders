package com.malky.go_orders.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malky.go_orders.screens.events.RestaurantEvents
import com.malky.go_orders.state.RestaurantInfoUIState.MenuOptions
import com.malky.go_orders.ui.theme.Beiruti
import com.malky.go_orders.ui.theme.GoOrdersTheme

@Composable
fun MenuOptionsBar(
    option: MenuOptions,
    onEvent: (RestaurantEvents) -> Unit,
    restaurantID: Int
) {
    MenuOptionsBarContent(
        option = option,
        onEvent = onEvent,
        restaurantID = restaurantID
    )
}

@Composable
private fun MenuOptionsBarContent(
    option: MenuOptions,
    onEvent: (RestaurantEvents) -> Unit,
    restaurantID: Int
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(vertical = 8.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .clickable {
                    onEvent(RestaurantEvents.FetchMenuItems(restaurantID))
                    onEvent(RestaurantEvents.SelectOption(MenuOptions.OnlineOrders))
                }
                .background(if (option == MenuOptions.OnlineOrders) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.secondary)
                .padding(vertical = 6.dp, horizontal = 12.dp),
            text = "أطلب أونلاين",
            fontFamily = Beiruti,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = if (option == MenuOptions.OnlineOrders) MaterialTheme.colorScheme.primary.copy(
                alpha = 0.9f
            )
            else MaterialTheme.colorScheme.onTertiary
        )
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .clickable {
                    onEvent(RestaurantEvents.FetchMenuImages(restaurantID))
                    onEvent(RestaurantEvents.SelectOption(MenuOptions.MenuImages))
                }
                .background(if (option == MenuOptions.MenuImages) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.secondary)
                .padding(vertical = 6.dp, horizontal = 12.dp),
            text = "صور المنيو",
            fontFamily = Beiruti,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = if (option == MenuOptions.MenuImages) MaterialTheme.colorScheme.primary.copy(
                alpha = 0.9f
            )
            else MaterialTheme.colorScheme.onTertiary
        )
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .clickable {
                    onEvent(RestaurantEvents.SelectOption(MenuOptions.RestaurantInfo))
                }
                .background(if (option == MenuOptions.RestaurantInfo) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.secondary)
                .padding(vertical = 6.dp, horizontal = 12.dp),
            text = "بيانات المطعم",
            fontFamily = Beiruti,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = if (option == MenuOptions.RestaurantInfo) MaterialTheme.colorScheme.primary.copy(
                alpha = 0.9f
            )
            else MaterialTheme.colorScheme.onTertiary
        )
    }
}

@Preview(locale = "ar")
@Composable
private fun PreviewMenuOptionBar() {
    GoOrdersTheme {
        MenuOptionsBar(
            option = MenuOptions.OnlineOrders,
            onEvent = {},
            restaurantID = 1)
    }
}