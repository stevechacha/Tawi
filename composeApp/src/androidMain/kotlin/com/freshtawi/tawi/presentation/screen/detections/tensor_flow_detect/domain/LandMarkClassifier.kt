package com.freshtawi.tawi.presentation.screen.detections.tensor_flow_detect.domain

import android.graphics.Bitmap
import com.freshtawi.tawi.presentation.screen.detections.tensor_flow_detect.domain.Classification

interface LandMarkClassifier {
    fun classify(bitmap: Bitmap,rotation: Int): List<Classification>
}