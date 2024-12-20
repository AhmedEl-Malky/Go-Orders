package com.malky.go_orders.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.malky.go_orders.screens.events.ExploreRestaurantsEvents
import com.malky.go_orders.state.ExploreRestaurantsUIState.CategoryUIState
import com.malky.go_orders.ui.theme.Beiruti
import com.malky.go_orders.ui.theme.GoOrdersTheme

@Composable
fun CategoryCard(
    category: CategoryUIState,
    onEvent: (ExploreRestaurantsEvents) -> Unit
) {
    CategoryCardContent(category, onEvent)
}


@Composable
private fun CategoryCardContent(
    category: CategoryUIState,
    onEvent: (ExploreRestaurantsEvents) -> Unit
) {
    Card(
        modifier = Modifier
            .size(80.dp),
        onClick = { onEvent(ExploreRestaurantsEvents.OnSelectCategory(category)) },
        shape = RoundedCornerShape(8.dp),
        border = if (category.isSelected)
            BorderStroke(0.8f.dp, color = MaterialTheme.colorScheme.primary)
        else
            BorderStroke(0.8f.dp, color = MaterialTheme.colorScheme.outline),
        colors = if (category.isSelected)
            CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
        else
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AsyncImage(
                modifier = Modifier.size(40.dp),
                model = category.icon,
                contentDescription = category.name
            )
            Text(
                text = category.name,
                color = if (category.isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                fontFamily = Beiruti,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
        }
    }

}


@Preview
@Composable
private fun PreviewCategoryCard() {
    GoOrdersTheme {
        CategoryCard(
            CategoryUIState(name = "مأكولات بحرية"),
            onEvent = {}
        )
    }
}
