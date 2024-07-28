package com.mashup.gabbangzip.sharedalbum.data.service

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Url

interface AwsService {
    @Multipart
    @PUT
    suspend fun uploadFile(
        @Url uploadUrl: String,
        @Part file: MultipartBody.Part,
    ): Response<Unit>

    companion object {
        const val BASE_URL = "https://pic-api-bucket.s3.ap-northeast-2.amazonaws.com/"
    }
}
