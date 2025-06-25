package com.example.goorders.restaurant.presentation.components

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
import com.example.goorders.core.presentation.theme.Beiruti
import com.example.goorders.core.presentation.theme.GoOrdersTheme
import com.example.goorders.restaurant.domain.MenuOptions
import com.example.goorders.restaurant.presentation.RestaurantActions

@Composable
fun MenuOptionsBar(
    option: MenuOptions,
    onAction: (RestaurantActions) -> Unit,
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
                    onAction(RestaurantActions.GetRestaurantInfo(restaurantID))
                    onAction(RestaurantActions.OnOptionSelect(MenuOptions.ONLINE_ORDERS))
                }
                .background(if (option == MenuOptions.ONLINE_ORDERS) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.secondary)
                .padding(vertical = 6.dp, horizontal = 12.dp),
            text = "أطلب أونلاين",
            fontFamily = Beiruti,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = if (option == MenuOptions.ONLINE_ORDERS) MaterialTheme.colorScheme.primary.copy(
                alpha = 0.9f
            )
            else MaterialTheme.colorScheme.onTertiary
        )
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .clickable {
                    onAction(RestaurantActions.GetMenuImages(restaurantID))
                    onAction(RestaurantActions.OnOptionSelect(MenuOptions.MENU_IMAGES))
                }
                .background(if (option == MenuOptions.MENU_IMAGES) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.secondary)
                .padding(vertical = 6.dp, horizontal = 12.dp),
            text = "صور المنيو",
            fontFamily = Beiruti,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = if (option == MenuOptions.MENU_IMAGES) MaterialTheme.colorScheme.primary.copy(
                alpha = 0.9f
            )
            else MaterialTheme.colorScheme.onTertiary
        )
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .clickable {
                    onAction(RestaurantActions.OnOptionSelect(MenuOptions.RESTAURANT_INFO))
                }
                .background(if (option == MenuOptions.RESTAURANT_INFO) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.secondary)
                .padding(vertical = 6.dp, horizontal = 12.dp),
            text = "بيانات المطعم",
            fontFamily = Beiruti,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = if (option == MenuOptions.RESTAURANT_INFO) MaterialTheme.colorScheme.primary.copy(
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
            option = MenuOptions.ONLINE_ORDERS,
            onAction = {},
            restaurantID = 1)
    }
}