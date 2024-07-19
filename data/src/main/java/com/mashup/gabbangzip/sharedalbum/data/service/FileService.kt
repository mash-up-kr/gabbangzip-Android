package com.mashup.gabbangzip.sharedalbum.data.service

import com.mashup.gabbangzip.sharedalbum.data.base.PicResponse
import com.mashup.gabbangzip.sharedalbum.data.dto.response.FileUploadResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FileService {
    @GET("api/v1/files/upload")
    suspend fun getFileUploadUrl(
        @Query("extension") extension: String,
    ): PicResponse<FileUploadResponse>
}
