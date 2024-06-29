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
