package com.coreStructure.core

import com.coreStructure.remote.ApiResponseCallback
import com.coreStructure.remote.model.CommonErrorModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection

open class BaseService {

    fun <T> makeRequest(call: Call<T>, responseCallback: ApiResponseCallback<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    response.body()?.let { responseCallback.onSuccess(it, response.headers()) }
                } else {
                    handleApiError(response, responseCallback)
                }
            }

            override fun onFailure(call: Call<T>, error: Throwable) {
                if (error is HttpException) {
                    val msg = when (error.code()) {
                        HttpsURLConnection.HTTP_UNAUTHORIZED -> "Session expired, please login again"
                        HttpsURLConnection.HTTP_FORBIDDEN -> "Forbidden Error"
                        HttpsURLConnection.HTTP_INTERNAL_ERROR -> "Internal Server Error"
                        HttpsURLConnection.HTTP_BAD_REQUEST -> "Bad Request"
                        else -> "HTTP Error: ${error.code()}"
                    }
                    responseCallback.onError(CommonErrorModel(title = "Network error", description = msg))
                } else {
                    responseCallback.onError(CommonErrorModel(title = "Unknown error", description = error.message.toString()))
                }
            }

        })
    }

    /**
     * Handles API error responses.
     *
     * @param response The [Response] object containing the error.
     * @param responseCallback A callback to handle the error response.
     */
    private fun <T> handleApiError(response: Response<T>, responseCallback: ApiResponseCallback<T>) {
        when (response.code()) {
            401 -> {
                responseCallback.onError(
                    CommonErrorModel(
                        description = "Session got expired please login again",
                        title = "Session Expire"
                    )
                )
            }
            400 -> {
                val error = Gson().fromJson(response.errorBody()?.charStream(), CommonErrorModel::class.java)
                responseCallback.onError(error)
            }
            else -> {
                responseCallback.onError(CommonErrorModel(description = response.message(), title = "HTTP Error: ${response.code()}"))
            }
        }
    }
}