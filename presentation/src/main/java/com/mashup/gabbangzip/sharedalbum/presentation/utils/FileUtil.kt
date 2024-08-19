package com.mashup.gabbangzip.sharedalbum.presentation.utils

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.OpenableColumns
import java.io.File
import java.io.FileOutputStream

object FileUtil {
    fun getFileFromUri(context: Context, uri: Uri?): File? {
        if (uri == null) return null
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getFileFromUriSDK33AndAbove(context, uri)
        } else {
            getFileFromUriBelowSDK33(context, uri)
        }
    }

    private fun getFileFromUriSDK33AndAbove(context: Context, uri: Uri): File? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        return context.contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(columnIndex)?.let { File(it) }
        }
    }

    private fun getFileFromUriBelowSDK33(context: Context, uri: Uri): File? {
        val fileName = getFileName(context, uri) ?: return null
        val file = File(context.cacheDir, fileName)
        runCatching {
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                FileOutputStream(file).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            return file
        }
        return null
    }

    private fun getFileName(context: Context, uri: Uri): String? {
        context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val displayNameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (displayNameIndex != -1) {
                    return cursor.getString(displayNameIndex)
                }
            }
        }
        return null
    }
}
