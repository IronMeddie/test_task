package com.ironmeddie.test_task.domain.models

data class BestSeller(
    val discount_price: Int,
    val id: Int,
    val is_favorites: Boolean,
    val picture: String,
    val price_without_discount: Int,
    val title: String
)