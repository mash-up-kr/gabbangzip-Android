package com.mashup.gabbangzip.sharedalbum.domain.usecase

import com.mashup.gabbangzip.sharedalbum.domain.repository.FileRepository
import java.io.File
import java.io.IOException
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(
    private val fileRepository: FileRepository,
) {
    suspend operator fun invoke(file: File): Result<String> {
        return runCatching {
            val fileUploadDomainModel = fileRepository.getUploadLink(file.extension)
            val response = fileRepository.uploadImage(
                url = fileUploadDomainModel.uploadUrl,
                imageFile = file,
            )
            if (response.isSuccess) {
                fileUploadDomainModel.fileId
            } else {
                throw IOException(response.errorMessage)
            }
        }
    }
}
