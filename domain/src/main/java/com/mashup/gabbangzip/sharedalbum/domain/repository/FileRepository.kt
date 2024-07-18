package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.model.FileUploadDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.ResponseDomainModel
import java.io.File

interface FileRepository {
    suspend fun getUploadLink(extension: String): FileUploadDomainModel
    suspend fun uploadImage(url: String, imageFile: File): ResponseDomainModel
}
