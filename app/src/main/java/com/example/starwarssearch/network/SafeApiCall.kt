package com.example.starwarssearch.network

open class SafeApiCall {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return
    }

}