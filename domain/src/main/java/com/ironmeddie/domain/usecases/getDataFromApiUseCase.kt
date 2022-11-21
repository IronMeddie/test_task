package com.ironmeddie.domain.usecases

import com.ironmeddie.domain.data.MyRepository
import javax.inject.Inject

class getDataFromApiUseCase @Inject constructor(private val myRepository: MyRepository) {
    suspend fun getMainScreenData() = myRepository.getMainScreenData()
    suspend fun getDetailsData() = myRepository.getDetails()
    suspend fun getCartData() = myRepository.getCart()
}