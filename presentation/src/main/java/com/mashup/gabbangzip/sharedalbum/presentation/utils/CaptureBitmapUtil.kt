package com.mashup.gabbangzip.sharedalbum.presentation.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Picture
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawModifierNode
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.core.content.FileProvider
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.util.UUID
import androidx.compose.ui.graphics.Canvas as ComposeCanvas

/**
 * [CaptureController.captureAsync] 를 호출했을 때 현재 Composable 의 [Bitmap] 을 반환하는 유틸 함수
 *
 * modifier 안으로 [Picture] 객체를 전달하는 일반적인 방식은 Composable 함수 내부에 [AsyncImage] 가 있을 때
 * AsyncImage 가 로드되기 전에 [Picture] 객체에 그림을 그려버리는 문제점이 발생함
 *
 * compose 1.7.0-alpha07 이상 (compose-bom 2024.09.01 이상) 부터는 [rememberGraphicsLayer] 을 제공 하고
 * 있어서, compose-bom 2024.09.01 이후엔 이를 활용 해보는 것도 좋아보임
 *
 * @see (https://blog.shreyaspatil.dev/capturing-composable-to-a-bitmap-without-losing-a-state)[link]
 * @see (https://github.com/PatilShreyas/Capturable/tree/master)[reference]
 */
fun Modifier.capturable(controller: CaptureController): Modifier {
    return this then CapturableModifierNodeElement(controller)
}

@SuppressLint("ModifierNodeInspectableProperties")
private data class CapturableModifierNodeElement(
    private val controller: CaptureController
) : ModifierNodeElement<CapturableModifierNode>() {
    override fun create(): CapturableModifierNode {
        return CapturableModifierNode(controller)
    }

    override fun update(node: CapturableModifierNode) {
        node.updateController(controller)
    }
}

private class CapturableModifierNode(
    controller: CaptureController
) : DelegatingNode(), DelegatableNode {

    /**
     * State to hold the current [CaptureController] instance.
     * This can be updated via [updateController] method.
     */
    private val currentController = MutableStateFlow(controller)

    override fun onAttach() {
        super.onAttach()
        coroutineScope.launch {
            observeCaptureRequestsAndServe()
        }
    }

    /**
     * Sets new [CaptureController]
     */
    fun updateController(newController: CaptureController) {
        currentController.value = newController
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun observeCaptureRequestsAndServe() {
        currentController
            .flatMapLatest { it.captureRequests }
            .collect { request ->
                val completable = request.imageBitmapDeferred
                try {
                    val picture = getCurrentContentAsPicture()
                    val bitmap = withContext(Dispatchers.Default) {
                        picture.createBitmap(request.config)
                    }
                    completable.complete(bitmap)
                } catch (error: Throwable) {
                    completable.completeExceptionally(error)
                }
            }
    }

    private suspend fun getCurrentContentAsPicture(): Picture {
        return Picture().apply { drawCanvasIntoPicture(this) }
    }

    /**
     * Draws the current content into the provided [picture]
     */
    private suspend fun drawCanvasIntoPicture(picture: Picture) {
        // CompletableDeferred to wait until picture is drawn from the Canvas content
        val pictureDrawn = CompletableDeferred<Unit>()

        // Delegate the task to draw the content into the picture
        val delegatedNode = delegate(
            CacheDrawModifierNode {
                val width = this.size.width.toInt()
                val height = this.size.height.toInt()

                onDrawWithContent {
                    val pictureCanvas = ComposeCanvas(picture.beginRecording(width, height))

                    draw(this, this.layoutDirection, pictureCanvas, this.size) {
                        this@onDrawWithContent.drawContent()
                    }
                    picture.endRecording()

                    drawIntoCanvas { canvas ->
                        canvas.nativeCanvas.drawPicture(picture)

                        // Notify that picture is drawn
                        pictureDrawn.complete(Unit)
                    }
                }
            }
        )
        // Wait until picture is drawn
        pictureDrawn.await()

        // As task is accomplished, remove the delegation of node to prevent draw operations on UI
        // updates or recompositions.
        undelegate(delegatedNode)
    }
}

class CaptureController {
    private val _captureRequests = MutableSharedFlow<CaptureRequest>(extraBufferCapacity = 1)
    internal val captureRequests = _captureRequests.asSharedFlow()

    fun captureAsync(config: Bitmap.Config = Bitmap.Config.ARGB_8888): Deferred<Bitmap> {
        val deferredImageBitmap = CompletableDeferred<Bitmap>()
        return deferredImageBitmap.also {
            _captureRequests.tryEmit(CaptureRequest(imageBitmapDeferred = it, config = config))
        }
    }

    internal class CaptureRequest(
        val imageBitmapDeferred: CompletableDeferred<Bitmap>,
        val config: Bitmap.Config
    )
}

@Composable
fun rememberCaptureController(): CaptureController {
    return remember { CaptureController() }
}

fun Picture.createBitmap(config: Bitmap.Config = Bitmap.Config.ARGB_8888): Bitmap {
    val bitmap = Bitmap.createBitmap(
        width,
        height,
        config,
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
