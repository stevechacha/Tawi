
package com.freshtawi.tawi.presentation.screen.detections.object_detection.detection.gallery

import android.net.Uri
import android.os.SystemClock
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.freshtawi.tawi.presentation.screen.detections.object_detection.objectdetector.ObjectDetectorHelper
import com.freshtawi.tawi.presentation.screen.detections.object_detection.composables.ResultsOverlay
import com.freshtawi.tawi.presentation.screen.detections.object_detection.utils.getFittedBoxSize
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.objectdetector.ObjectDetectionResult
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit



@Composable
fun VideoDetectionView(
    threshold: Float,
    maxResults: Int,
    delegate: Int,
    mlModel: Int,
    setInferenceTime: (Int) -> Unit,
    videoUri: Uri,
) {
    var isPlaying by remember { mutableStateOf(false) }
    var videoHeight by remember { mutableIntStateOf(1) }
    var videoWidth by remember { mutableIntStateOf(1) }
    var results by remember { mutableStateOf<ObjectDetectionResult?>(null) }

    // We use ExoPlayer to play our video from the uri with no sounds
    val exoPlayer = ExoPlayer.Builder(LocalContext.current)
        .build()
        .also { exoPlayer ->
            val mediaItem = MediaItem.Builder()
                .setUri(videoUri).build()
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.volume = 0f
        }

    val context = LocalContext.current
    val backgroundExecutor = Executors.newSingleThreadScheduledExecutor()
    backgroundExecutor.execute {
        val objectDetectorHelper =
            ObjectDetectorHelper(
                context = context,
                threshold = threshold,
                currentDelegate = delegate,
                currentModel = mlModel,
                maxResults = maxResults,
                runningMode = RunningMode.VIDEO,
            )

        val videoInterval = 300L

        val detectionResults = objectDetectorHelper.detectVideoFile(videoUri, videoInterval)

        if (detectionResults != null) {
            val videoStartTimeMs = SystemClock.uptimeMillis()

            isPlaying = true

            backgroundExecutor.scheduleWithFixedDelay(
                {
                    val videoElapsedTimeMs =
                        SystemClock.uptimeMillis() - videoStartTimeMs
                    val resultIndex =
                        videoElapsedTimeMs.div(videoInterval).toInt()

                    if (resultIndex >= detectionResults.results.size) {
                        backgroundExecutor.shutdown()
                    } else {
                        results = detectionResults.results[resultIndex]
                        setInferenceTime(detectionResults.inferenceTime.toInt())
                    }
                },
                0,
                videoInterval,
                TimeUnit.MILLISECONDS,
            )

        }
        objectDetectorHelper.clearObjectDetector()
    }

    // On disposal of this composable, we release the exo player
    DisposableEffect(Unit) {
        onDispose { exoPlayer.release() }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {

        val boxSize = getFittedBoxSize(
            containerSize = Size(
                width = this.maxWidth.value,
                height = this.maxHeight.value,
            ),
            boxSize = Size(
                width = videoWidth.toFloat(),
                height = videoHeight.toFloat()
            )
        )

        Box(
            modifier = Modifier
                .width(boxSize.width.dp)
                .height(boxSize.height.dp)
        ) {
            AndroidView(
                factory = {
                    StyledPlayerView(context).apply {
                        hideController()
                        useController = false
                        player = exoPlayer
                    }
                },
                update = {
                    Log.i("Big Chungus", "VideoDetectionView: Updated")
                    if (isPlaying) {
                        videoHeight = exoPlayer.videoSize.height
                        videoWidth = exoPlayer.videoSize.width
                        exoPlayer.play()
                    }
                }
            )
            results?.let {
                ResultsOverlay(
                    results = it,
                    frameWidth = videoWidth,
                    frameHeight = videoHeight,
                )
            }
        }
        // While the object detection is in progress, we display a circular progress indicator
        if (!isPlaying) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}