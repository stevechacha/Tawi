package com.freshtawi.tawi.presentation.screen.detections.tensor_flow_detect.data

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.freshtawi.tawi.presentation.screen.detections.object_detection.utils.centreCrop
import com.freshtawi.tawi.presentation.screen.detections.tensor_flow_detect.domain.Classification
import com.freshtawi.tawi.presentation.screen.detections.tensor_flow_detect.domain.LandMarkClassifier

class LandMarkImageAnalyser(
    private val classifier: LandMarkClassifier,
    private val onResult: (List<Classification>)->Unit
) : ImageAnalysis.Analyzer{

    private var frameSkipCentre = 0
    override fun analyze(image: ImageProxy) {

        if (frameSkipCentre % 60 == 0){
            val rotationDegree = image.imageInfo.rotationDegrees
            val bitmap = image
                .toBitmap()
                .centreCrop(321,321)

            val results = classifier.classify(bitmap,rotationDegree)
            onResult(results)
        }
        frameSkipCentre++

        image.close()

    }
}