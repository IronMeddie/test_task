package com.ironmeddie.domain.usecases

import com.ironmeddie.data.DataResource
import com.ironmeddie.domain.data.MyRepository
import com.ironmeddie.domain.models.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock


class getDataFromApiUseCaseTest {

    private val userRepository = mock<MyRepository>()

    @Test
    fun shoudReturnTheSameDataAsInRepository() {
        runBlocking {
            val testData = DataResource.Success(ResponseMainScreen(listOf(BestSeller(), BestSeller(), BestSeller(id = 8), BestSeller(), BestSeller()),listOf(HomeStore(), HomeStore())))

            Mockito.`when`(userRepository.getMainScreenData()).thenReturn(testData)

            val expectedListHomeStore = listOf(HomeStore(), HomeStore())
            val expectedListBestSeller =
                listOf(BestSeller(), BestSeller(), BestSeller(id = 8), BestSeller(), BestSeller())
            val expected = DataResource.Success(
                (ResponseMainScreen(
                    expectedListBestSeller,
                    expectedListHomeStore
                ))
            )
            val usecase = getDataFromApiUseCase(myRepository = userRepository)
            val actual = usecase.getMainScreenData()
            Assertions.assertEquals(expected, actual)
        }
    }
}