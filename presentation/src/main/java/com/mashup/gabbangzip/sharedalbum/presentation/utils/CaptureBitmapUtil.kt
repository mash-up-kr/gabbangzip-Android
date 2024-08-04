package com.mashup.gabbangzip.sharedalbum.presentation.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Picture
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas

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
