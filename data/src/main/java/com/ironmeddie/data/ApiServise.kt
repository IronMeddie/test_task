package com.ironmeddie.data

import com.ironmeddie.domain.models.Cart
import com.ironmeddie.domain.models.Details
import com.ironmeddie.domain.models.ResponseMainScreen
import retrofit2.http.GET

interface ApiServise {

    @GET("/v3/654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getMainScreenInfo() : ResponseMainScreen


    @GET("/v3/6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getDetails() : Details


    @GET("/v3/53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getCart() : Cart


}