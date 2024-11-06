package com.freshtawi.tawi.presentation.screen.detections.object_detection.detection.camera


import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.objectdetector.ObjectDetectionResult
import com.freshtawi.tawi.presentation.screen.detections.object_detection.composables.ResultsOverlay
import com.freshtawi.tawi.presentation.screen.detections.object_detection.objectdetector.ObjectDetectorHelper
import com.freshtawi.tawi.presentation.screen.detections.object_detection.objectdetector.ObjectDetectorListener
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.BoundingBox
import com.freshtawi.tawi.presentation.screen.detections.object_detection.utils.getFittedBoxSize
import java.io.File
import androidx.lifecycle.viewmodel.compose.viewModel
import com.freshtawi.tawi.presentation.screen.detections.object_detection.DetectionsViewModel
import java.util.concurrent.Executor
import java.util.concurrent.Executors




@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraView(
    threshold: Float,
    maxResults: Int,
    delegate: Int,
    mlModel: Int,
    setInferenceTime: (newInferenceTime: Int) -> Unit,
) {
    val viewModel = viewModel<DetectionsViewModel>()
    val bitmaps by viewModel.bitmaps.collectAsState()
    // We first have to deal with the camera permission, so we declare a state for it
    val cameraPermissionState: PermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    LaunchedEffect(key1 = Unit) {
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    if (!cameraPermissionState.status.isGranted) {
        Text(text = "No Camera Permission!")
        return
    }

    var results by remember { mutableStateOf<ObjectDetectionResult?>(null) }
    var frameHeight by remember { mutableIntStateOf(4) }
    var frameWidth by remember { mutableIntStateOf(3) }
    var active by remember { mutableStateOf(true) }

    // We need the following objects setup the camera preview later
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val cameraController = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or
                        CameraController.VIDEO_CAPTURE
            )
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            active = false
            cameraProviderFuture.get().unbindAll()
        }
    }

    // Next we describe the UI of this camera view.
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        val cameraPreviewSize = getFittedBoxSize(
            containerSize = Size(
                width = this.maxWidth.value,
                height = this.maxHeight.value,
            ),
            boxSize = Size(
                width = frameWidth.toFloat(),
                height = frameHeight.toFloat()
            )
        )

        Box(
            Modifier
                .width(cameraPreviewSize.width.dp)
                .height(cameraPreviewSize.height.dp),
        ) {
            AndroidView(
                factory = { ctx ->
                    val previewView = PreviewView(ctx)
                    val executor = ContextCompat.getMainExecutor(ctx)
                    cameraProviderFuture.addListener({
                        val cameraProvider = cameraProviderFuture.get()

                        val preview = Preview.Builder().build().also {
                            it.setSurfaceProvider(previewView.surfaceProvider)
                        }

                        val cameraSelector = CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build()


                        val imageAnalyzer =
                            ImageAnalysis.Builder()
                                .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                                .build()


                        val backgroundExecutor = Executors.newSingleThreadExecutor()

                        backgroundExecutor.execute {
                            val objectDetectorHelper =
                                ObjectDetectorHelper(
                                    context = ctx,
                                    threshold = threshold,
                                    currentDelegate = delegate,
                                    currentModel = mlModel,
                                    maxResults = maxResults,
                                    objectDetectorListener = ObjectDetectorListener(
                                        onErrorCallback = { _, _ -> },
                                        onResultsCallback = {
                                            frameHeight = it.inputImageHeight
                                            frameWidth = it.inputImageWidth
                                            if (active) {
                                                results = it.results.first()
                                                setInferenceTime(it.inferenceTime.toInt())
                                            }
                                        }
                                    ),
                                    runningMode = RunningMode.LIVE_STREAM
                                )

                            imageAnalyzer.setAnalyzer(
                                backgroundExecutor,
                                objectDetectorHelper::detectLivestreamFrame
                            )
                        }

                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            imageAnalyzer,
                            preview
                        )
                    }, executor)
                    // We return our preview view from the AndroidView factory to display it
                    previewView
                },
                modifier = Modifier.fillMaxSize(),
            )
            // Finally, we check for current results, if there's any, we display the results overlay
            results?.let {
                ResultsOverlay(
                    results = it,
                    frameWidth = frameWidth,
                    frameHeight = frameHeight
                )
            }

            val executor = ContextCompat.getMainExecutor(context)

            // The floating action button here is used to launch an activity to select a media file from
            FloatingActionButton(
                onClick = {
                    takePhoto(cameraController,onPhotoTaken = viewModel::onTakePhoto,context)
//                    takePicture(cameraController, executor)
                },
                containerColor = BoundingBox,
                contentColor = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .absoluteOffset((-20).dp, (-10).dp)
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = null,
                )
            }
        }
    }
}

private fun takePicture(cameraController: LifecycleCameraController, executor: Executor) {
    val file = File.createTempFile("images", ".jpg")
    val outputDirectory = ImageCapture.OutputFileOptions.Builder(file).build()
    cameraController.takePicture(
        outputDirectory,
        executor,
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                println(outputFileResults.savedUri)
            }

            override fun onError(exception: ImageCaptureException) {
                println()
            }
        })
}


private fun takePhoto(
    controller: LifecycleCameraController,
    onPhotoTaken: (Bitmap) -> Unit,
    context: Context
) {
    controller.takePicture(
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)

                val matrix = Matrix().apply {
                    postRotate(image.imageInfo.rotationDegrees.toFloat())
                }
                val rotatedBitmap = Bitmap.createBitmap(
                    image.toBitmap(),
                    0,
                    0,
                    image.width,
                    image.height,
                    matrix,
                    true
                )

                onPhotoTaken(rotatedBitmap)
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Log.e("Camera", "Couldn't take photo: ", exception)
            }
        }
    )
}



