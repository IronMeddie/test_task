package com.ironmeddie.di

import com.ironmeddie.domain.helper.Constance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun baseurl() = Constance.BASE_URL
    @Provides
    fun logging() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    @Provides
    fun okHttpClient() = OkHttpClient.Builder().addInterceptor(logging()).build()
    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): com.ironmeddie.data.ApiServise =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).client(
            okHttpClient()
        ).build().create(com.ironmeddie.data.ApiServise::class.java)
}