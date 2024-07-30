package com.mashup.gabbangzip.sharedalbum.data.service

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Url

interface AwsService {
    @Multipart
    @PUT
    suspend fun uploadFile(
        @Header("Content-Type") contentType: String,
        @Url uploadUrl: String,
        @Part file: MultipartBody.Part,
    ): Response<Unit>
}
