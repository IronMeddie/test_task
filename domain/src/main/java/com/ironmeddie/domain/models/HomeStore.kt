package com.ironmeddie.domain.models

data class HomeStore(
    val id: Int = 0,
    val is_buy: Boolean = false,
    val is_new: Boolean = false,
    val picture: String = "https://i.pinimg.com/originals/57/f3/23/57f3234f65f0f327e7b86860c5cebd71.jpg",
    val subtitle: String = "TestSubtitle",
    val title: String = "TestTitle"
)