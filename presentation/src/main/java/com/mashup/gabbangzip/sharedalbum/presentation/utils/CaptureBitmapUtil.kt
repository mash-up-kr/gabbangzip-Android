package com.mashup.gabbangzip.sharedalbum.presentation.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Picture
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.util.UUID
import androidx.compose.ui.graphics.Canvas as ComposeCanvas

fun Modifier.captureIntoCanvas(
    picture: Picture,
): Modifier = this.then(
    drawWithCache {
        // Example that shows how to redirect rendering to an Android Picture and then
        // draw the picture into the original destination
        val width = this.size.width.toInt()
        val height = this.size.height.toInt()
        onDrawWithContent {
            val pictureCanvas = ComposeCanvas(
                picture.beginRecording(
                    width,
                    height,
                ),
            )
            draw(this, this.layoutDirection, pictureCanvas, this.size) {
                this@onDrawWithContent.drawContent()
            }
            picture.endRecording()

            drawIntoCanvas { canvas -> canvas.nativeCanvas.drawPicture(picture) }
        }
    },
)

fun Picture.createBitmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(
        width,
        height,
        Bitmap.Config.ARGB_8888,
    )

    val canvas = Canvas(bitmap)
    canvas.drawPicture(this)
    return bitmap
}

private const val IMAGE_CACHE_DIR = "images"
private const val IMAGE_FILE_NAME_PREFIX = "shared_image"
private const val FILE_PROVIDER_AUTHORITY = "provider"
private const val IMAGE_MIME_TYPE = "image/png"
private const val SHARE_IMAGE_CHOOSER_TITLE = "Share Image"

fun Context.shareBitmap(bitmap: Bitmap) {
    val cachePath = File(applicationContext.cacheDir, IMAGE_CACHE_DIR)
    cachePath.mkdirs()

    val file = File(cachePath, "${IMAGE_FILE_NAME_PREFIX}_${UUID.randomUUID()}.png")
    FileOutputStream(file).use { outputStream ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    }

    // Grant URI permission
    val contentUri = FileProvider.getUriForFile(
        applicationContext,
        "${applicationContext.packageName}.$FILE_PROVIDER_AUTHORITY",
        file,
    )

    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, contentUri)
        type = IMAGE_MIME_TYPE
        // Grant temporary read permission
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    startActivity(Intent.createChooser(shareIntent, SHARE_IMAGE_CHOOSER_TITLE))
}
