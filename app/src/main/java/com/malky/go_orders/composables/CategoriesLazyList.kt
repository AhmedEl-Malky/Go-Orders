package com.malky.go_orders.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malky.go_orders.screens.events.ExploreRestaurantsEvents
import com.malky.go_orders.state.ExploreRestaurantsUIState
import com.malky.go_orders.state.ExploreRestaurantsUIState.CategoryUIState
import com.malky.go_orders.ui.theme.GoOrdersTheme

@Composable
fun CategoriesLazyList(
    categories: List<CategoryUIState>,
    onEvent: (ExploreRestaurantsEvents) -> Unit
) {
    CategoriesLazyListContent(categories,onEvent)
}

@Composable
private fun CategoriesLazyListContent(
    categories: List<CategoryUIState>,
    onEvent: (ExploreRestaurantsEvents) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 8.dp),
    ) {
        items(categories) {
            CategoryCard(it,onEvent)
        }
    }
}

@Preview
@Composable
private fun PreviewCategoriesLazyList() {
    GoOrdersTheme {
        CategoriesLazyList(ExploreRestaurantsUIState().categories,{})
    }
}
