package com.ironmeddie.base

import com.ironmeddie.base.Constants.NO_INTERNET
import com.ironmeddie.base.Constants.OTHER_ERROR
import com.ironmeddie.data.DataResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException

abstract class baseRepository {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): DataResource<T> {
        return withContext(Dispatchers.IO) {
            try {
                DataResource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        DataResource.Failure(false, throwable.code(),
                            throwable.response()?.errorBody()?.string()
                                ?.let { JSONObject(it).getString("message") }, throwable.message)
                    }
                    else -> {
                        if (throwable.message == NO_INTERNET) {
                            DataResource.Failure(true, null, null, throwable.message)
                        } else DataResource.Failure(true, OTHER_ERROR, null, throwable.message)
                    }
                }
            }
        }
    }

}

object Constants {
    const val NO_INTERNET = "NO_INTERNET"
    const val OTHER_ERROR = 999
}