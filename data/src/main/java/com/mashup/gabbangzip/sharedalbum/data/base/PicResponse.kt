package com.mashup.gabbangzip.sharedalbum.data.base

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PicResponse<T>(
    val isSuccess: Boolean,
    val data: T?,
    val errorResponse: PicErrorResponse?,
)

@JsonClass(generateAdapter = true)
data class PicErrorResponse(
    val code: String,
    val message: String,
)
