package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextOutLineButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.primary,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    height: Dp = 34.dp,
    border: BorderStroke = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary)
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.height(height),
        shape = shape,
        border = border,
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.W600,
            color = textColor
        )
    }
}