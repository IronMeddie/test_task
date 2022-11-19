package com.ironmeddie.data


import javax.inject.Inject

class Repository @Inject constructor(private val newsService: ApiServise) {

     suspend fun getMainInfo() = newsService.getMainScreenInfo()
     suspend fun getDetails() = newsService.getDetails()
     suspend fun getCart() = newsService.getCart()
}
