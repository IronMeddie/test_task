package com.ironmeddie.domain.data

import com.ironmeddie.data.DataResource
import com.ironmeddie.domain.models.Cart
import com.ironmeddie.domain.models.Details
import com.ironmeddie.domain.models.ResponseMainScreen

interface MyRepository {
    suspend fun getMainScreenData(): DataResource<ResponseMainScreen>
    suspend fun getDetails(): DataResource<Details>
    suspend fun getCart(): DataResource<Cart>
}