
package com.freshtawi.tawi.presentation.screen.detections.object_detection.detection.gallery

import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.BoundingBox


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun GalleryView(
    threshold: Float,
    maxResults: Int,
    delegate: Int,
    mlModel: Int,
    setInferenceTime: (Int) -> Unit,
) {

    var selectedMediaUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        // On selecting an image or a video, we update the selected media uri
        onResult = { uri ->
            selectedMediaUri = uri
        },
    )

    Box(modifier = Modifier.fillMaxSize()) {
        val selectedMediaType = selectedMediaUri?.let {
            val mimeType = LocalContext.current.contentResolver.getType(it)
            if (mimeType == null) {
                "Unknown"
            } else if (mimeType.startsWith("image")) {
                "Image"
            } else if (mimeType.startsWith("video")){
                "Video"
            } else {
                "Unknown"
            }
        }

        when (selectedMediaType) {
            "Image" -> ImageDetectionView(
                threshold = threshold,
                maxResults = maxResults,
                delegate = delegate,
                mlModel = mlModel,
                setInferenceTime = setInferenceTime,
                imageUri = selectedMediaUri!!,
            )
            "Video" -> VideoDetectionView(
                threshold = threshold,
                maxResults = maxResults,
                delegate = delegate,
                mlModel = mlModel,
                setInferenceTime = setInferenceTime,
                videoUri = selectedMediaUri!!,
            )
            "Unknown" -> Text(
                text = "Unknown media type",
                modifier = Modifier.align(
                    Alignment.Center
                ),
            )
            // In this case, the selected media uri is null, i.e, nothing is selected
            else -> Text(
                text = "Click + to add an image or a video to begin running the object detection.",
                modifier = Modifier.align(Alignment.Center).padding(20.dp),
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontSize = 13.sp
            )
        }

        // The floating action button here is used to launch an activity to select a media file from
        FloatingActionButton(
            onClick = {
                selectedMediaUri = null
                galleryLauncher.launch(arrayOf("image/*", "video/*"))
            },
            containerColor = BoundingBox,
            contentColor = Color.White,
            modifier = Modifier.align(Alignment.BottomEnd).absoluteOffset((-20).dp, (-10).dp)
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = null,
            )
        }
    }
}




