package com.example.goorders.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goorders.core.presentation.theme.GoOrdersTheme
import com.example.goorders.home.domain.Category
import com.example.goorders.home.presentation.HomeActions


@Composable
fun CategoriesLazyList(
    categories: List<Category>,
    selectedCategory: Category,
    onCategorySelect: (Category) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 8.dp),
    ) {
        items(categories) { category ->
            CategoryCard(
                category = category,
                selectedCategory = selectedCategory,
                onCategorySelect = onCategorySelect
            )
        }
    }
}

@Preview
@Composable
private fun PreviewCategoriesLazyList() {
    GoOrdersTheme {
        CategoriesLazyList(
            categories = emptyList(),
            selectedCategory = Category(
                id = 1,
                name = "Pizza",
                slug = "pizza",
                icon = "https://example.com/pizza_icon.png",
                order = 1
            ),
            onCategorySelect = {}
        )
    }
}
