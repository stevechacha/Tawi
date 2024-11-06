package com.freshtawi.tawi.presentation.screen.detections.object_detection.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun hasCameraPermission(context: Context)= ContextCompat.checkSelfPermission(
    context,
    Manifest.permission.CAMERA

) == PackageManager.PERMISSION_GRANTED