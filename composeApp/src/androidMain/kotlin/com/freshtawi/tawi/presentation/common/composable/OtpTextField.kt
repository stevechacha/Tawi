package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun OtpTextField(inputValue: MutableState<String>, onNextFocus: MutableState<String>?) {
        OutlinedTextField(
            value = inputValue.value,
            onValueChange = {
                if (it.length <= 1) {
                    inputValue.value = it
                    if (it.isNotEmpty()) onNextFocus?.value = ""
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.size(50.dp),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        )

}