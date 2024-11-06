package com.freshtawi.tawi.presentation.screen.detections.object_detection

import android.Manifest
import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.freshtawi.tawi.presentation.screen.detections.object_detection.composables.MediaPipeBanner
import com.freshtawi.tawi.presentation.screen.detections.object_detection.composables.TabsTopBar
import com.freshtawi.tawi.presentation.screen.detections.object_detection.detection.camera.CameraView
import com.freshtawi.tawi.presentation.screen.detections.object_detection.detection.gallery.GalleryView
import com.freshtawi.tawi.presentation.screen.detections.object_detection.objectdetector.ObjectDetectorHelper
import com.freshtawi.tawi.presentation.screen.detections.object_detection.utils.hasCameraPermission


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun DetectionMainScreen(
    onOptionsButtonClick:()->Unit,
) {
    val threshold by rememberSaveable { mutableStateOf(0.4f) }
    val maxResults by rememberSaveable { mutableStateOf(5) }
    val delegate by rememberSaveable { mutableStateOf(ObjectDetectorHelper.DELEGATE_CPU) }
    val mlModel by rememberSaveable { mutableStateOf(ObjectDetectorHelper.MODEL_EFFICIENTDETV0) }

    val context = LocalContext.current

    if (!hasCameraPermission(context)){
        ActivityCompat.requestPermissions(
            context as Activity, arrayOf(Manifest.permission.CAMERA),0
        )
    }


    DetectionsHomeScreen(
        onOptionsButtonClick = onOptionsButtonClick,
        threshold = threshold,
        maxResults = maxResults,
        delegate = delegate,
        mlModel = mlModel,
    )
}


@RequiresApi(Build.VERSION_CODES.P)
@Composable
private fun DetectionsHomeScreen(
    onOptionsButtonClick: () -> Unit,
    threshold: Float,
    maxResults: Int,
    delegate: Int,
    mlModel: Int,
) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    var inferenceTime by rememberSaveable { mutableIntStateOf(0) }

    Column {
        MediaPipeBanner(
            onOptionsButtonClick = onOptionsButtonClick,
        )
        // The tabs at the top to switch between camera and gallery views
        TabsTopBar(
            selectedTabIndex = selectedTabIndex,
            setSelectedTabIndex = {
                selectedTabIndex = it
                inferenceTime = 0
            }
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            if (selectedTabIndex == 0) {
                CameraView(
                    threshold = threshold,
                    maxResults = maxResults,
                    delegate = delegate,
                    mlModel = mlModel,
                    setInferenceTime = { inferenceTime = it },
                )
            }
            else {
                GalleryView(
                    threshold = threshold,
                    maxResults = maxResults,
                    delegate = delegate,
                    mlModel = mlModel,
                    setInferenceTime = { inferenceTime = it },
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(10.dp),
        ) {
            Text(text = "Inference Time: $inferenceTime ms")
        }
    }
}

