package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.BottomBarColor

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    bottomText: String? = null,
    text: String,
    enable: Boolean = true,
    onClick: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (bottomText != null) {
            Text(
                text = bottomText,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.W700,
                lineHeight = 15.sp,
                fontSize = 10.sp,
                textAlign = TextAlign.Start
            )
        }

        Button(
            onClick = { onClick() },
            shape = RoundedCornerShape(16.dp),
            enabled = enable,
            modifier = modifier.fillMaxWidth().height(58.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (enable) MaterialTheme.colorScheme.primary else BottomBarColor
            )
        ) {
            Text(
                text = text.uppercase(),
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.W700,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
        }
    }
}