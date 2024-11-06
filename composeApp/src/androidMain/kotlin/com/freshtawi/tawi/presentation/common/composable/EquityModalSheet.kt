package com.freshtawi.tawi.presentation.common.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun VunaModalSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
     BoxWithConstraints {
         Surface {
             ModalBottomSheet(
                 sheetState = sheetState,
                 onDismissRequest = {
                     onDismissRequest()
                 },
                 modifier = modifier,
                 dragHandle = null,
                 shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
                 containerColor = MaterialTheme.colorScheme.background
             ) {
                 Column(
                     modifier = Modifier.fillMaxWidth()
                         .safeGesturesPadding()
                 ) {
                     content()
                 }
             }
         }
     }

}


