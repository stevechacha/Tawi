package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.presentation.common.composable.theme.boarderTextField

@Composable
fun SpTextField(
    modifier: Modifier = Modifier,
    label: String = "",
    errorMessage: String = "",
    shape: Shape = RoundedCornerShape(16.dp),
    textFieldModifier: Modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    correctValidation: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = errorMessage.isNotEmpty()
) {
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium
        )
        OutlinedTextField(
            modifier = textFieldModifier,
            value = value,
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            onValueChange = onValueChange,
            shape = shape,
            textStyle = MaterialTheme.typography.bodySmall.copy(MaterialTheme.colorScheme.onPrimary),
            singleLine = singleLine,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            trailingIcon = if (keyboardType == KeyboardType.Password) {
                { TrailingIcon(showPassword) { showPassword = !showPassword } }
            } else null,
            visualTransformation = tfVisualTransformation(keyboardType, showPassword),
            isError = isError,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedBorderColor = boarderTextField,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                errorBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                errorCursorColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.onTertiary,
            ),
        )

        AnimatedVisibility(isError) {
            Text(
                text = errorMessage,
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }

    }
}


@Composable
private fun ContainerColor(isError: Boolean, correctValidation: Boolean): Color {
    return if (isError) {
        MaterialTheme.colorScheme.primary
    } else if (correctValidation) {
        boarderTextField
    } else {
        MaterialTheme.colorScheme.surface
    }
}

@Composable
private fun TrailingIcon(
    showPassword: Boolean,
    togglePasswordVisibility: () -> Unit
) {
    IconButton(onClick = { togglePasswordVisibility() }) {
        Icon(
            imageVector = if (showPassword) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
            contentDescription = if (showPassword) "Show Password" else "Hide Password",
            tint = MaterialTheme.colorScheme.onTertiary
        )
    }

}

@Composable
private fun tfVisualTransformation(
    keyboardType: KeyboardType,
    showPassword: Boolean
): VisualTransformation {
    return if (showPassword || keyboardType != KeyboardType.Password && keyboardType != KeyboardType.NumberPassword) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }
}
