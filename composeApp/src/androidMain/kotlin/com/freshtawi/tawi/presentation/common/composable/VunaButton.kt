package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.BottomBarColor

@Composable
fun VunaButton(
    text: String,
    enable: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(8.dp),
        enabled = enable,
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enable) BottomBarColor else BottomBarColor
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