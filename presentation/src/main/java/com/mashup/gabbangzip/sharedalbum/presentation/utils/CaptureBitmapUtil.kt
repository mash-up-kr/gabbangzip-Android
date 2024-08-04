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

fun Modifier.captureIntoCanvas(
    picture: Picture,
): Modifier = this.then(
    drawWithCache {
        // Example that shows how to redirect rendering to an Android Picture and then
        // draw the picture into the original destination
        val width = this.size.width.toInt()
        val height = this.size.height.toInt()
        onDrawWithContent {
            val pictureCanvas =
                androidx.compose.ui.graphics.Canvas(
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

fun Context.shareBitmap(bitmap: Bitmap) {
    val cachePath = File(applicationContext.cacheDir, "images")
    cachePath.mkdirs()

    val file = File(cachePath, "shared_image.png")
    FileOutputStream(file).use { outputStream ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    }

    // Grant URI permission
    val contentUri = FileProvider.getUriForFile(
        applicationContext,
        "${applicationContext.packageName}.provider",
        file,
    )

    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, contentUri)
        type = "image/png"
        // Grant temporary read permission
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    startActivity(Intent.createChooser(shareIntent, "Share Image"))
}
