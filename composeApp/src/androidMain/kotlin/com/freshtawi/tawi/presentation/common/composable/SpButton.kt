package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerButtons
import androidx.compose.ui.unit.dp

@Composable
fun SpButton(
    modifier: Modifier = Modifier,
    enable: Boolean = false,
    textButtons: String,
    onClick: ()->Unit
) {
    Button(
        onClick = onClick,
        enabled = enable,
        modifier = modifier.fillMaxWidth().height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enable) MaterialTheme.colorScheme.primary else Color.Gray,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(16.dp)
    ){
        Text(text =  textButtons)
    }

}