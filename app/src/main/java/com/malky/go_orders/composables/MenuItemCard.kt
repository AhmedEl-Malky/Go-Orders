package com.malky.go_orders.composables

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.malky.go_orders.R
import com.malky.go_orders.state.RestaurantInfoUIState.*
import com.malky.go_orders.ui.theme.Beiruti

@Composable
fun MenuItemCard(
    menuCategory: MenuCategoryUIState,
    menuSize:Int,
    index:Int,
    fetchMenuItems:(String) -> List<MenuItemUIState>
) {
    MenuItemCardContent(
        menuCategory = menuCategory,
        menuSize = menuSize,
        index = index,
        fetchMenuItems = fetchMenuItems
    )
}

@Composable
private fun MenuItemCardContent(
    menuCategory: MenuCategoryUIState,
    menuSize: Int,
    index:Int,
    fetchMenuItems: (String) -> List<MenuItemUIState>
) {
    var isClicked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            onClick = {},
            border = BorderStroke(
                width = 1.5f.dp,
                color = MaterialTheme.colorScheme.outline
            ),
            shape = RoundedCornerShape(6.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = menuCategory.category,
                    fontFamily = Beiruti,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Icon(
                    imageVector = if (isClicked) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Menu Icon",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        if (menuCategory.isSelected) {
            fetchMenuItems(menuCategory.category).forEach { item ->
                Card(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(80.dp)
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.outline,
                                    shape = RoundedCornerShape(6.dp)
                                ),
                            model = item.image,
                            contentDescription = null,
                        )
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = item.name?:"NULL",
                                fontFamily = Beiruti,
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = item.description?:"NULL",
                                fontFamily = Beiruti,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                color = MaterialTheme.colorScheme.onTertiary
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        modifier = Modifier.size(18.dp),
                                        painter = painterResource(R.drawable.coin),
                                        contentDescription = "price",
                                        tint = MaterialTheme.colorScheme.onBackground
                                    )
                                    Text(
                                        text = " السعر : ",
                                        fontFamily = Beiruti,
                                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                        color = MaterialTheme.colorScheme.onBackground,
                                        fontWeight = FontWeight.Medium
                                    )
                                    Text(
                                        text = "${item.sellingPrice}ج",
                                        fontFamily = Beiruti,
                                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.outlineVariant
                                    )
                                }
                                Button(
                                    modifier = Modifier.height(30.dp),
                                    contentPadding = PaddingValues(
                                        vertical = 2.dp,
                                        horizontal = 4.dp
                                    ),
                                    onClick = {},
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primary.copy(0.3f)
                                    ),
                                    shape = RoundedCornerShape(6.dp)
                                ) {
                                    Icon(
                                        modifier = Modifier.size(18.dp),
                                        painter = painterResource(R.drawable.add),
                                        contentDescription = "Add to Cart",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(Modifier.width(2.dp))
                                    Text(
                                        text = "أضف للسلة",
                                        fontFamily = Beiruti,
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }

                        }
                    }

                }
            }
        }
    }
}

//val items = listOf(1, 2, 2)

@Preview(locale = "ar")
@Composable
private fun PreviewMenuItemCard() {
//    GoOrdersTheme {
//        MenuItemCard(
//            menuItems = emptyList(),
//            menuSize = 10,
//            index = 10
//        )
//    }
}
