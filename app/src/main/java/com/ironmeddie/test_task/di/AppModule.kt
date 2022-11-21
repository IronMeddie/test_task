package com.ironmeddie.test_task.di

import com.ironmeddie.domain.data.MyRepository
import com.ironmeddie.domain.usecases.getDataFromApiUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent




@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    fun providesGetDataFromApiUseCase(myRepository: MyRepository) : getDataFromApiUseCase = getDataFromApiUseCase(myRepository)

}