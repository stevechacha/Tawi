package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.presentation.common.composable.modifier.noRippleEffect

@Composable
fun SpSimpleTextField(
    text: String,
    hint: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit = {},
    hintColor: Color = MaterialTheme.colorScheme.onTertiary,
    modifier: Modifier = Modifier,
    trailingPainter: Painter? = null,
    leadingPainter: Painter? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    errorMessage: String = "",
    isError: Boolean = errorMessage.isNotEmpty(),
    onTrailingIconClick: () -> Unit = {},
    isSingleLine: Boolean = true,
    trailingIconEnabled: Boolean = onTrailingIconClick != {},
    outlinedTextFieldDefaults: TextFieldColors = OutlinedTextFieldColorDefaults()
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp, max = 160.dp),
            value = text,
            placeholder = {
                Text(
                    hint,
                    style = MaterialTheme.typography.bodyMedium,
                    color = hintColor,
                )
            },
            onValueChange = onValueChange,
            shape = RoundedCornerShape(16.dp),
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimary),
            singleLine = isSingleLine,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            isError = isError,
            trailingIcon = {
                trailingPainter?.let {
                    IconButton(
                        onClick = onTrailingIconClick,
                        enabled = trailingIconEnabled,
                    ) {
                        Icon(
                            painter = trailingPainter,
                            contentDescription = "trailing icon",
                            tint = MaterialTheme.colorScheme.onTertiary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            },
            leadingIcon = if (leadingPainter != null) {
                {
                    Icon(
                        painter = leadingPainter,
                        contentDescription = "leading icon",
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.noRippleEffect(onClick)
                    )
                }
            } else null,
            colors = outlinedTextFieldDefaults,
        )
        AnimatedVisibility(isError) {
            Text(
                text = errorMessage,
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

}

@Composable
fun OutlinedTextFieldColorDefaults() = OutlinedTextFieldDefaults.colors(
    focusedContainerColor = MaterialTheme.colorScheme.background,
    unfocusedContainerColor = MaterialTheme.colorScheme.background,
    cursorColor = MaterialTheme.colorScheme.onTertiary,
    errorCursorColor = MaterialTheme.colorScheme.primary,
    focusedBorderColor = MaterialTheme.colorScheme.primary,
    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
    errorBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
)
