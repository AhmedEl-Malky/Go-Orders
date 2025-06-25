package com.example.goorders.restaurant.presentation.components

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.composables.icons.lucide.CircleDollarSign
import com.composables.icons.lucide.CirclePlus
import com.composables.icons.lucide.Lucide
import com.example.goorders.core.presentation.theme.Beiruti
import com.example.goorders.core.presentation.theme.GoOrdersTheme
import com.example.goorders.restaurant.domain.MenuCategories
import com.example.goorders.restaurant.domain.MenuItem


@Composable
fun MenuItemCard(
    category: String,
    menuSize: Int,
    index: Int,
    menuItems: List<MenuItem>,
    restaurantLogo:String
) {
    var isClicked by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(if (index == menuSize - 1) PaddingValues(bottom = 16.dp) else PaddingValues(0.dp))
            .clip(
                if (index == menuSize - 1) RoundedCornerShape(
                    bottomEnd = 6.dp,
                    bottomStart = 6.dp
                ) else RoundedCornerShape(0.dp)
            )
            .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f))
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
                .padding(
                    if (index == menuSize - 1) PaddingValues(
                        bottom = 16.dp,
                        top = 8.dp,
                        start = 16.dp,
                        end = 16.dp
                    ) else PaddingValues(vertical = 8.dp, horizontal = 16.dp)
                ),
            onClick = { isClicked = !isClicked },
            border = BorderStroke(
                width = 1.5f.dp,
                color = MaterialTheme.colorScheme.outline
            ),
            shape = RoundedCornerShape(6.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = category,
                    fontFamily = Beiruti,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = if (isClicked) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Menu Icon",
                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }
        }
        if (isClicked) {
            menuItems.filter { it.category == category }.forEach { item ->
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
                                .clip(RoundedCornerShape(6.dp))
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.outline,
                                    shape = RoundedCornerShape(6.dp)
                                ),
                            model = item.image?: restaurantLogo,
                            contentDescription = null,
                        )
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = item.name,
                                fontFamily = Beiruti,
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = item.description,
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
                                        imageVector = Lucide.CircleDollarSign,
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
                                        imageVector = Lucide.CirclePlus,
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


@Preview(locale = "ar")
@Composable
private fun PreviewMenuItemCard() {
    GoOrdersTheme {
        MenuItemCard(
            category = "المأكولات",
            menuSize = 1,
            index = 0,
            menuItems = emptyList(),
            restaurantLogo = ""
        )
    }
}
