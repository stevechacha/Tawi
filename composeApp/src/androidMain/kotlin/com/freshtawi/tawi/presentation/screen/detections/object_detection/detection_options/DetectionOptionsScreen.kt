package com.freshtawi.tawi.presentation.screen.detections.object_detection.detection_options

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.R
import com.freshtawi.tawi.presentation.screen.detections.object_detection.composables.MediaPipeBanner
import com.freshtawi.tawi.presentation.screen.detections.object_detection.objectdetector.ObjectDetectorHelper
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.BoundingBox
import kotlin.math.max
import kotlin.math.min

@Composable
fun DetectionOptionsScreen(
    navigateBack:()->Unit
) {
    var threshold by rememberSaveable { mutableFloatStateOf(0.4f) }
    var maxResults by rememberSaveable { mutableIntStateOf(5) }
    var delegate by rememberSaveable { mutableIntStateOf(ObjectDetectorHelper.DELEGATE_CPU) }
    var mlModel by rememberSaveable { mutableIntStateOf(ObjectDetectorHelper.MODEL_EFFICIENTDETV0) }
    OptionsScreen(
        onBackButtonClick = navigateBack,
        threshold = threshold, setThreshold = { threshold = it },
        maxResults = maxResults, setMaxResults = { maxResults = it },
        delegate = delegate, setDelegate = { delegate = it },
        mlModel = mlModel, setMlModel = { mlModel = it },
    )
}



@Composable
private fun OptionsScreen(
    threshold: Float, setThreshold: (Float) -> Unit,
    maxResults: Int, setMaxResults: (Int) -> Unit,
    delegate: Int, setDelegate: (Int) -> Unit,
    mlModel: Int, setMlModel: (Int) -> Unit,
    onBackButtonClick: () -> Unit,
) {
    var delegateDropdownExpanded by remember { mutableStateOf(false) }
    var mlModelDropdownExpanded by remember { mutableStateOf(false) }

    // Now we define the UI.
    Column {
        MediaPipeBanner(onBackButtonClick = onBackButtonClick,)
        Box(
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Options",
                fontSize = 25.sp,
            )
        }
        HorizontalDivider()

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
        ) {
            Text(
                text = "Threshold",
                modifier = Modifier.weight(1f),
            )
            IconButton(
                onClick = {
                    val newThreshold = ((threshold * 10).toInt() - 1).toDouble() / 10
                    setThreshold(max(newThreshold.toFloat(), 0.0f,))
                },
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_minus),
                    contentDescription = null,
                    tint = BoundingBox
                )
            }
            Box(
                modifier = Modifier.width(50.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "$threshold".substring(IntRange(0, 2)),
                )
            }
            IconButton(
                onClick = {
                    val newThreshold = ((threshold * 10).toInt() + 1).toDouble() / 10
                    setThreshold(min(newThreshold.toFloat(), 0.8f,))
                },
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_plus),
                    contentDescription = null,
                    tint = BoundingBox
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
        ) {
            Text(
                text = "Max Results",
                modifier = Modifier.weight(1f),
            )
            IconButton(
                onClick = { setMaxResults(max(maxResults - 1, 1,)) },
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_minus),
                    contentDescription = null,
                    tint = BoundingBox
                )
            }
            Box(
                modifier = Modifier.width(50.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "$maxResults",)
            }
            IconButton(
                onClick = { setMaxResults(min(maxResults + 1, 5,))},
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_plus),
                    contentDescription = null,
                    tint = BoundingBox
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
        ) {
            Text(
                text = "Delegate",
                modifier = Modifier.weight(1f),
            )
            Text(
                text = when (delegate) {
                    ObjectDetectorHelper.DELEGATE_CPU -> "CPU";
                    else -> "GPU";
                },
            )
            IconButton(
                onClick = { delegateDropdownExpanded = true},
            ) {
                Icon(
                    Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    tint = BoundingBox
                )
                DropdownMenu(
                    expanded = delegateDropdownExpanded,
                    onDismissRequest = { delegateDropdownExpanded = false },
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "CPU") },
                        onClick = {
                            setDelegate(ObjectDetectorHelper.DELEGATE_CPU)
                            delegateDropdownExpanded = false
                        },
                    )
                    DropdownMenuItem(
                        text = { Text(text = "GPU") },
                        onClick = {
                            setDelegate(ObjectDetectorHelper.DELEGATE_GPU)
                            delegateDropdownExpanded = false
                        },
                    )
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
        ) {
            Text(
                text = "ML Model",
                modifier = Modifier.weight(1f),
            )
            Text(
                text = when (mlModel) {
                    ObjectDetectorHelper.MODEL_MANGO -> "Mango Model Lite0";
                    ObjectDetectorHelper.MODEL_EFFICIENTDETV0 -> "Mango Model Lite0";
                    ObjectDetectorHelper.MODEL_EFFICIENTDETV2 -> "Mango Model Lite0";
                    else -> "Mango Model Lite0";
                },
            )
            IconButton(
                onClick = {
                    mlModelDropdownExpanded = true
                },
            ) {
                Icon(
                    Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    tint = BoundingBox
                )
                DropdownMenu(
                    expanded = mlModelDropdownExpanded,
                    onDismissRequest = { mlModelDropdownExpanded = false },
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "Mango Model") },
                        onClick = {
                            setMlModel(ObjectDetectorHelper.MODEL_MANGO)
                            mlModelDropdownExpanded = false
                        },
                    )
                    DropdownMenuItem(
                        text = { Text(text = "EfficientDet Lite0") },
                        onClick = {
                            setMlModel(ObjectDetectorHelper.MODEL_EFFICIENTDETV0)
                            mlModelDropdownExpanded = false
                        },
                    )
                    DropdownMenuItem(
                        text = { Text(text = "EfficientDet Lite2") },
                        onClick = {
                            setMlModel(ObjectDetectorHelper.MODEL_EFFICIENTDETV2)
                            mlModelDropdownExpanded = false
                        },
                    )
                }
            }
        }
    }
}