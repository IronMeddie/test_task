package com.ironmeddie.domain.models

data class BestSeller(
    val discount_price: Int = 20,
    val id: Int = 0,
    val is_favorites: Boolean = false,
    val picture: String = "https://million-wallpapers.ru/wallpapers/6/10/491806811297888/pejzazh-lestnica-na-prirode-cherez-vody.jpg",
    val price_without_discount: Int = 30,
    val title: String = "title"
)