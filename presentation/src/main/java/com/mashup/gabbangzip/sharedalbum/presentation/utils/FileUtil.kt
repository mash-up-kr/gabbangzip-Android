package com.mashup.gabbangzip.sharedalbum.presentation.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.OpenableColumns
import java.io.ByteArrayOutputStream
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

    fun getJpgImage(context: Context, uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream).run {
            adjustBitmapOrientation(context, uri, this)
        }

        val outputStream = ByteArrayOutputStream().apply {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, this)
        }

        val jpgData = outputStream.toByteArray()

        return File.createTempFile("compress_image", ".jpeg", context.cacheDir)
            .apply {
                FileOutputStream(this).use { fileOutputStream ->
                    fileOutputStream.write(jpgData)
                }
            }
            .also {
                outputStream.close()
                inputStream?.close()
            }
    }

    private fun adjustBitmapOrientation(context: Context, uri: Uri, bitmap: Bitmap): Bitmap {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return bitmap
        val exifAttr = ExifInterface(inputStream).getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL,
        )
        val rotation = when (exifAttr) {
            ExifInterface.ORIENTATION_ROTATE_90 -> 90f
            ExifInterface.ORIENTATION_ROTATE_180 -> 180f
            ExifInterface.ORIENTATION_ROTATE_270 -> 270f
            else -> 0f
        }

        inputStream.close()

        return if (rotation != 0f) {
            val matrix = Matrix().apply { postRotate(rotation) }
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
                .also {
                    if (it != bitmap) {
                        bitmap.recycle()
                    }
                }
        } else {
            bitmap
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
