package com.mashup.gabbangzip.sharedalbum.data.repository

import com.mashup.gabbangzip.sharedalbum.data.base.callApi
import com.mashup.gabbangzip.sharedalbum.data.service.AwsService
import com.mashup.gabbangzip.sharedalbum.data.service.FileService
import com.mashup.gabbangzip.sharedalbum.domain.model.FileUploadDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.PicNetworkResponseDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.FileRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val fileService: FileService,
    private val awsService: AwsService,
) : FileRepository {
    override suspend fun getUploadLink(extension: String): FileUploadDomainModel {
        val response = callApi {
            fileService.getFileUploadUrl(extension = extension)
        }
        return FileUploadDomainModel(
            uploadUrl = response.uploadUrl,
            fileId = response.fileId,
        )
    }

    override suspend fun uploadImage(url: String, imageFile: File): PicNetworkResponseDomainModel {
        val multipart = MultipartBody.Part.createFormData(
            name = "file",
            filename = imageFile.name,
            body = imageFile.asRequestBody("image/${imageFile.extension}".toMediaTypeOrNull()),
        )
        return awsService.uploadFile(url, multipart).let { response ->
            PicNetworkResponseDomainModel(
                isSuccess = response.isSuccessful,
                errorMessage = response.errorBody()?.string().toString(),
            )
        }
    }
}
