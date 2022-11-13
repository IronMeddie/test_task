package com.ironmeddie.test_task.domain.di

import com.ironmeddie.test_task.data.ApiServise
import com.ironmeddie.test_task.domain.helper.Constance.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun baseurl() = BASE_URL
    @Provides
    fun logging() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    @Provides
    fun okHttpClient() = OkHttpClient.Builder().addInterceptor(logging()).build()
    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): ApiServise =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).client(
            okHttpClient()
        ).build().create(ApiServise::class.java)
}