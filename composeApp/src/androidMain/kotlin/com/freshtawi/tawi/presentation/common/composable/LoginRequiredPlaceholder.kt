package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.presentation.common.resources.Resources


@Composable
fun LoginRequiredPlaceholder(
    placeHolder: Painter,
    message: String,
    modifier: Modifier = Modifier,
    onClickLogin: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = placeHolder,
            contentDescription = Resources.strings.login,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(112.dp).padding(bottom = 8.dp)
        )
        Text(
            text = message,
            fontSize = 14.sp,
            lineHeight = 19.6.sp,
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colorScheme.onTertiary,
        )
        TextOutLineButton(
            modifier = Modifier.width(115.dp).padding(top = 24.dp),
            text = Resources.strings.login,
            onClick = { onClickLogin() }
        )
    }
}