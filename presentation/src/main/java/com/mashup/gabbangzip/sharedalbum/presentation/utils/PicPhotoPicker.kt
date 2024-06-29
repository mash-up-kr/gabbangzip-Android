package com.mashup.gabbangzip.sharedalbum.presentation.utils

import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.annotation.IntRange

class PicPhotoPicker private constructor(
    private val launcher: ActivityResultLauncher<PickVisualMediaRequest>,
) {
    fun open() {
        launcher.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
    }

    companion object {
        /**
         * 이미지 1개 가져오기
         *
         * (주의) STARTED 이전에 호출해야 함
         */
        fun create(
            activity: ComponentActivity,
            onImagePicked: (Uri?) -> Unit,
        ): PicPhotoPicker {
            val launcher = activity.registerForActivityResult(PickVisualMedia()) { uri ->
                onImagePicked(uri)
            }
            return PicPhotoPicker(launcher)
        }

        /**
         * 이미지 여러개 가져오기
         *
         * (주의) STARTED 이전에 호출해야 함
         */
        fun create(
            activity: ComponentActivity,
            @IntRange(from = 2) max: Int,
            onImagePicked: (List<Uri?>) -> Unit,
        ): PicPhotoPicker {
            val launcher =
                activity.registerForActivityResult(PickMultipleVisualMedia(max)) { uris ->
                    onImagePicked(uris)
                }
            return PicPhotoPicker(launcher)
        }
    }
}
