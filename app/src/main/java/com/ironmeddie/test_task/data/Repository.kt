package com.ironmeddie.test_task.data

import javax.inject.Inject

class Repository @Inject constructor(private val newsService: ApiServise){

    suspend fun getMainInfo() = newsService.getMainScreenInfo()
    suspend fun getDetails() = newsService.getDetails()

}
