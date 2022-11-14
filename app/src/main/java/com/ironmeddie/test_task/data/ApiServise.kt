package com.ironmeddie.test_task.data

import com.ironmeddie.test_task.domain.models.Details
import com.ironmeddie.test_task.domain.models.ResponseMainScreen
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServise {

    @GET("/v3/654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getMainScreenInfo() : Response<ResponseMainScreen>


    @GET("/v3/6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getDetails() : Response<Details>


}