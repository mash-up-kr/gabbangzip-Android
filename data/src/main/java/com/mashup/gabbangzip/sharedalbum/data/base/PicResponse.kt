package com.mashup.gabbangzip.sharedalbum.data.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PicResponse<T>(
    @Json(name = "is_success")
    val isSuccess: Boolean,
    @Json(name = "data")
    val data: T?,
    @Json(name = "error_response")
    val errorResponse: PicErrorResponse?,
)

@JsonClass(generateAdapter = true)
data class PicErrorResponse(
    @Json(name = "code")
    val code: String,
    @Json(name = "message")
    val message: String,
)

data class PicApiException(
    val errorResponse: PicErrorResponse?,
) : RuntimeException() {
    override val message: String
        get() = errorResponse?.message.orEmpty()
}

suspend fun <T> callApi(
    execute: suspend () -> PicResponse<T>,
): T {
    return runCatching {
        execute().data ?: throw NoSuchElementException()
    }.getOrElse { e ->
        throw PicApiErrorParser.parse(e)
    }
}
