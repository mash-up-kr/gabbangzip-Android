package com.mashup.gabbangzip.sharedalbum.domain.usecase

import dagger.Reusable
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.io.File
import javax.inject.Inject

@Reusable
class UploadImageListUseCase @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase,
) {
    suspend operator fun invoke(fileList: List<File>): Result<List<String>> {
        return runCatching {
            coroutineScope {
                fileList.map { file ->
                    async {
                        uploadImageUseCase(file).getOrThrow()
                    }
                }.awaitAll()
            }
        }
    }
}
