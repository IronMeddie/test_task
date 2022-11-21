package com.ironmeddie.test_task.di

import com.ironmeddie.data.ApiServise
import com.ironmeddie.data.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.ironmeddie.domain.data.MyRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

 @Provides
 @Singleton
 fun providesRepository(apiServise: ApiServise) : MyRepository = Repository(apiServise)
}