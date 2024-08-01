package com.mashup.gabbangzip.sharedalbum.data.service

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Url

interface AwsService {
    @PUT
    suspend fun uploadFile(
        @Header("Content-Type") contentType: String,
        @Url uploadUrl: String,
        @Body requestBody: RequestBody,
    ): Response<Unit>
}
