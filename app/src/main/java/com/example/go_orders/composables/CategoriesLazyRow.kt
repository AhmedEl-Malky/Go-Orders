package com.example.go_orders.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.go_orders.state.ExploreRestaurantsScreenUIState
import com.example.go_orders.state.ExploreRestaurantsScreenUIState.Category

@Composable
fun CategoriesLazyRow(
    categories: List<Category>
) {
    CategoriesLazyRowContent(categories)
}

@Composable
private fun CategoriesLazyRowContent(
    categories: List<Category>
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
        reverseLayout = true
    ) {
        items(categories) {
            CategoryCard(it)
        }
    }
}

@Preview
@Composable
private fun PreviewCategoriesLazyRow() {
    CategoriesLazyRow(ExploreRestaurantsScreenUIState().categories)
}
