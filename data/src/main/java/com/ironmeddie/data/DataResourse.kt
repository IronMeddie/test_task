package com.ironmeddie.data

import okhttp3.ResponseBody

sealed class DataResource<out T> {
    data class Success<out T>(val value: T) : DataResource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?,
        val otherMessage: String?
    ) : DataResource<Nothing>()

    object Loading : DataResource<Nothing>()
}