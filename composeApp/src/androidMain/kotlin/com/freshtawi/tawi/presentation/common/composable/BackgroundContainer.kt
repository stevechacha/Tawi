package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BackgroundContainer(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ){
        Column(
            modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
        ) {
            Column { topBar() }
            Spacer(modifier = Modifier.height(5.dp))
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
            ) { content() }
        }
    }
}
