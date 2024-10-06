package com.example.go_orders.state

import com.example.go_orders.R


data class ExploreRestaurantsScreenUIState(
    val categories: List<Category> = listOf(
        Category(name = "الكل", img = R.drawable.all, isSelected = true),
        Category(name = "دجاج", img = R.drawable.chicken, isSelected = false),
        Category(name = "بيتزا", img = R.drawable.pizza, isSelected = false),
        Category(name = "برجر", img = R.drawable.burger, isSelected = false),
        Category(name = "كريب", img = R.drawable.crepe, isSelected = false),
        Category(name = "ساندويتش", img = R.drawable.sandwich, isSelected = false),
        Category(name = "شاورما", img = R.drawable.shawarma, isSelected = false),
        Category(name = "لحوم", img = R.drawable.meat, isSelected = false),
        Category(name = "كبده", img = R.drawable.liver, isSelected = false),
        Category(name = "مأكولات بحرية", img = R.drawable.spaghetti, isSelected = false),
        Category(name = "كشري", img = R.drawable.kabab, isSelected = false),
        Category(name = "فول وفلافل", img = R.drawable.falafel, isSelected = false),
        Category(name = "مخبوزات", img = R.drawable.backing, isSelected = false),
        Category(name = "سوشي", img = R.drawable.sushi, isSelected = false),
        Category(name = "حلويات", img = R.drawable.sweets, isSelected = false),
    ),
) {
    data class Category(
        val name: String,
        val img: Int,
        val isSelected: Boolean
    )
}