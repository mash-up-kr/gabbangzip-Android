package com.mashup.gabbangzip.sharedalbum.data.dto.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FileUploadResponse(
    @Json(name = "upload_url")
    val uploadUrl: String,
    @Json(name = "file_id")
    val fileId: String,
    @Json(name = "url_exp_ts_millis")
    val urlExpTsMillis: Long,
)
