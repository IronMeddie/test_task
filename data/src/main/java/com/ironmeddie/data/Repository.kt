package com.ironmeddie.data


import com.ironmeddie.base.baseRepository
import javax.inject.Inject

class Repository @Inject constructor(private val newsService: ApiServise) : baseRepository() {


     suspend fun getDetails() = safeApiCall { newsService.getDetails() }
     suspend fun getCart() = safeApiCall { newsService.getCart() }
     suspend fun getMainScreenSafe() = safeApiCall { newsService.getMainScreenInfo() }



}


