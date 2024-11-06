package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.presentation.common.composable.SimpleTextButton

@Composable
fun ResendText(
    modifier: Modifier = Modifier,
    firstText: String,
    clickedText: String,
    onClick: ()-> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = firstText,
            style = MaterialTheme.typography.titleMedium,
        )
        SimpleTextButton(
            modifier = Modifier.padding(vertical = 2.dp),
            text = clickedText,
            onClick = onClick,
            border = BorderStroke(width = 0.dp, color = Color.Transparent),
            textColor = Color(0xFF03539E)
        )
    }

}