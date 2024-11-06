package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.presentation.common.composable.util.getNavigationBarPadding
import com.freshtawi.tawi.presentation.common.composable.util.getStatusBarPadding

@Composable
fun HeadFirstCard(
    textHeader: String = "",
    textSubHeader: String = "",
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
            .padding(top = 56.dp)
            .padding(getStatusBarPadding())
            .padding(getNavigationBarPadding())
            .background(MaterialTheme.colorScheme.background),
    ) {
        Column(
            modifier = modifier.fillMaxWidth().padding(32.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center
        ) {
//            Icon(
//                modifier = Modifier.fillMaxWidth().height(100.dp),
//                painter = painterResource(logo),
//                contentDescription = null,
//            )
            Spacer(modifier = Modifier.height(24.dp))
            content()
        }
        CardHeader(
            indicatorAlignment = Alignment.BottomCenter,
            textHeader = textHeader,
            textSubHeader = textSubHeader,
            onClick = onClick
        )
    }
}

@Composable
private fun BoxScope.CardHeader(
    modifier: Modifier = Modifier,
    textHeader: String,
    textSubHeader: String,
    indicatorAlignment: Alignment,
    onClick:()->Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth().align(indicatorAlignment).padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = textHeader,
            style = MaterialTheme.typography.titleMedium,
        )
        SimpleTextButton(
            modifier = Modifier.padding(vertical = 2.dp),
            text = textSubHeader,
            onClick = onClick,
            border = BorderStroke(width = 0.dp, color = Color.Transparent),
        )
    }

}