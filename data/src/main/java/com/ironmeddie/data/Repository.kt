package com.ironmeddie.data


import com.ironmeddie.base.baseRepository
import com.ironmeddie.domain.data.MyRepository
import javax.inject.Inject

class Repository @Inject constructor(private val newsService: ApiServise) : baseRepository(), MyRepository {

     override suspend fun getDetails() = safeApiCall { newsService.getDetails() }
     override suspend fun getCart() = safeApiCall { newsService.getCart() }
     override suspend fun getMainScreenData() = safeApiCall { newsService.getMainScreenInfo() }

}


