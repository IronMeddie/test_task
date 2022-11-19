package com.ironmeddie.domain.models

data class Details(
    val CPU: String= "",
    val camera: String= "",
    val capacity: List<String> = listOf("0"),
    val color: List<String> = listOf("#FFFFFF"),
    val id: String = "",
    val images: List<String> = listOf(""),
    val isFavorites: Boolean = false,
    val price: Int = 0,
    val rating: Double = 0.0,
    val sd: String = "",
    val ssd: String = "",
    val title: String = ""
)