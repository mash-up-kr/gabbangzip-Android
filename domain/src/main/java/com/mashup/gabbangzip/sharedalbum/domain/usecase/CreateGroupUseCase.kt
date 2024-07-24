package com.mashup.gabbangzip.sharedalbum.domain.usecase

import com.mashup.gabbangzip.sharedalbum.domain.model.GroupParam
import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupInfoDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.FileRepository
import com.mashup.gabbangzip.sharedalbum.domain.repository.GroupRepository
import java.io.File
import java.io.IOException
import javax.inject.Inject

class CreateGroupUseCase @Inject constructor(
    private val groupRepository: GroupRepository,
    private val fileRepository: FileRepository,
) {
    suspend operator fun invoke(
        name: String,
        keyword: String,
        imageFile: File,
    ): Result<GroupInfoDomainModel> {
        return runCatching {
            val fileUploadDomainModel = fileRepository.getUploadLink(imageFile.extension)
            val response = fileRepository.uploadImage(
                url = fileUploadDomainModel.uploadUrl,
                imageFile = imageFile,
            )
            if (response.isSuccess) {
                groupRepository.createGroup(
                    GroupParam(
                        name = name,
                        keyword = keyword,
                        imageUrl = fileUploadDomainModel.fileId,
                    ),
                )
            } else {
                throw IOException(response.errorMessage)
            }
        }
    }
}
