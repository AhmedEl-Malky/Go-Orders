package com.example.goorders.home.domain

data class Restaurant(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val cover: String = "",
    val logo: String = "",
    val isOpen: Boolean = false,
    val categories: List<String> = emptyList()
)