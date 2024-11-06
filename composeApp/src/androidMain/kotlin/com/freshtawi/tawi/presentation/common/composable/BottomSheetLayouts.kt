package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetLayout(
    name: String,
    content: @Composable ColumnScope.() -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
    onDismissRequest: () -> Unit
) {
    val scope = rememberCoroutineScope()
    ModalBottomSheet(
        onDismissRequest = {
            if (sheetState.currentValue == SheetValue.Expanded && sheetState.hasPartiallyExpandedState) {
                scope.launch { sheetState.partialExpand() }
            } else { // Is expanded without collapsed state or is collapsed.
                scope.launch { sheetState.hide() }.invokeOnCompletion { onDismissRequest() }
            }
        },
        sheetState = sheetState
    ) {
        content()
    }

}