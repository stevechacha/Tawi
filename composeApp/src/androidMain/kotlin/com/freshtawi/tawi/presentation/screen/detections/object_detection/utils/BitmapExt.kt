package com.freshtawi.tawi.presentation.screen.detections.object_detection.utils

import android.graphics.Bitmap
import android.icu.text.DateTimePatternGenerator.DisplayWidth


fun Bitmap.centreCrop(desiredWidth: Int,desiredHeight: Int): Bitmap{
    val xStart = (width - desiredWidth) / 2
    val yStart  = (height - desiredHeight) / 2

    if (xStart< 0 || yStart < 0 || desiredWidth > width || desiredHeight > height){
        throw IllegalArgumentException("Invalid arguments for centre cropping")
    }
    return Bitmap.createBitmap(this,xStart,yStart,desiredWidth,desiredHeight)


}