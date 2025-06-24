package com.example.goorders.home.domain

data class Category(
    val id: Int,
    val name: String,
    val slug: String,
    val icon: String,
    val order: Int,
    val isSelected: Boolean = false,
)