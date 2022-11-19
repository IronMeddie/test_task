package com.ironmeddie.domain.models

data class ResponseMainScreen(
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)