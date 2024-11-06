package com.freshtawi.tawi.presentation.screen.detections.tensor_flow_detect.presentation

import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.freshtawi.tawi.presentation.screen.detections.tensor_flow_detect.data.LandMarkImageAnalyser
import com.freshtawi.tawi.presentation.screen.detections.tensor_flow_detect.data.TFLiteLandmarkClassifier
import com.freshtawi.tawi.presentation.screen.detections.tensor_flow_detect.domain.Classification

@Composable
fun LandMarkScreen() {
    val context = LocalContext.current


    var classifications by remember {
        mutableStateOf(emptyList<Classification>())
    }


    val analyser = remember {
        LandMarkImageAnalyser(
            classifier = TFLiteLandmarkClassifier(
                context = context
            ),
            onResult = {
                classifications = it
            }
        )
    }
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
            setImageAnalysisAnalyzer(
                ContextCompat.getMainExecutor(context),
                analyser
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        CameraPreview(controller = controller, modifier = Modifier.fillMaxSize())

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            classifications.forEach {
                Text(
                    text = it.name,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

}



