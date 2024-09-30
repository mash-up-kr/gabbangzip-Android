package com.mashup.gabbangzip.sharedalbum.data.base

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.ResponseBody
import retrofit2.HttpException

object PicApiErrorParser {
    private val moshi = Moshi.Builder().build()

    // PicResponse의 타입을 동적으로 처리하기 위해 ParameterizedType 생성
    private val type = Types.newParameterizedType(PicResponse::class.java, Any::class.java)
    private val adapter = moshi.adapter<PicResponse<Any>>(type)

    fun parse(e: Throwable): Throwable {
        return when (e) {
            is HttpException -> {
                val error = e.response()
                    ?.errorBody()
                    ?.let { parseErrorResponse(it) }
                PicApiException(error)
            }

            else -> e
        }
    }

    private fun parseErrorResponse(errorResponseBody: ResponseBody): PicErrorResponse? {
        return try {
            val response = adapter.fromJson(errorResponseBody.string())
            if (response != null && !response.isSuccess) {
                response.errorResponse
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
