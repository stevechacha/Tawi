
package com.freshtawi.tawi.presentation.screen.detections.object_detection.detection.gallery

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.presentation.screen.detections.object_detection.objectdetector.ObjectDetectorHelper
import com.freshtawi.tawi.presentation.screen.detections.object_detection.composables.ResultsOverlay
import com.freshtawi.tawi.presentation.screen.detections.object_detection.utils.getFittedBoxSize
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.objectdetector.ObjectDetectionResult
import java.util.concurrent.Executors


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun ImageDetectionView(
    threshold: Float,
    maxResults: Int,
    delegate: Int,
    mlModel: Int,
    imageUri: Uri,
    setInferenceTime: (Int) -> Unit,
) {
    var loadedImage by remember { mutableStateOf<Bitmap?>(null) }
    var results by remember { mutableStateOf<ObjectDetectionResult?>(null) }

    val context = LocalContext.current
    val source = ImageDecoder.createSource(context.contentResolver, imageUri)
    loadedImage = ImageDecoder.decodeBitmap(source)

    loadedImage?.copy(Bitmap.Config.ARGB_8888, true)?.let { image ->

        val backgroundExecutor = Executors.newSingleThreadScheduledExecutor()
        backgroundExecutor.execute {
            val objectDetectorHelper = ObjectDetectorHelper(
                context = context,
                threshold = threshold,
                currentDelegate = delegate,
                currentModel = mlModel,
                maxResults = maxResults,
                runningMode = RunningMode.IMAGE,
            )

            val resultBundle = objectDetectorHelper.detectImage(image)

            if (resultBundle != null) {
                setInferenceTime(resultBundle.inferenceTime.toInt())
                results = resultBundle.results.first()
            }
            objectDetectorHelper.clearObjectDetector()
        }
    }


    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        // We check if the image is loaded, then we display it
        loadedImage?.let { _loadedImage ->
            val imageBitmap = _loadedImage.asImageBitmap()

            val boxSize = getFittedBoxSize(
                containerSize = Size(
                    width = this.maxWidth.value,
                    height = this.maxHeight.value,
                ),
                boxSize = Size(
                    width = _loadedImage.width.toFloat(),
                    height = _loadedImage.height.toFloat()
                )
            )

            // Now that we have the exact UI size, we display the image and the results
            Box(
                modifier = Modifier
                    .width(boxSize.width.dp)
                    .height(boxSize.height.dp)
            ) {
                Image(
                    bitmap = imageBitmap,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                )
                results?.let { _results ->
                    ResultsOverlay(
                        results = _results,
                        frameWidth = _loadedImage.width,
                        frameHeight = _loadedImage.height,
                    )
                }
            }
        }
    }

}