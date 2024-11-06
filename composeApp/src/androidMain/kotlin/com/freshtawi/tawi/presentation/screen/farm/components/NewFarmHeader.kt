package com.freshtawi.tawi.presentation.screen.farm.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NewFarmHeaderFirst(
    modifier: Modifier = Modifier,
    topAppBar: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit ,
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = topAppBar,
        bottomBar = { Column(modifier.fillMaxWidth().padding(16.dp)) { bottomBar() } }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ContentScreen(
                modifier = modifier.align(Alignment.TopStart),
                content = content
            )

        }
    }

}

@Composable
private fun ContentScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        content()
    }
}
