package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.Poppins
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.TextHintColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.Font
import com.freshtawi.tawi.presentation.common.composable.theme.boarderTextField


@Composable
fun AppOutlinedTextField(
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    label: String = "",
    moreInfoText: String = "",
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = "",
    maxLines: Int = 1,
    trailingIcon: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    keyboardType: KeyboardType = KeyboardType.Text,
    focusRequester: FocusRequester = FocusRequester(),
    singleLine: Boolean = true,
    errorMessage: String = "",
    isError: Boolean = errorMessage.isNotEmpty(),
    correctValidation: Boolean = false
) {
    var showPassword by remember { mutableStateOf(false) }

    val colors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.background,
        focusedBorderColor = containerColor(isError, correctValidation),
        unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),

    )
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        if (showLabel) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Start,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = moreInfoText,
                    style = MaterialTheme.typography.labelMedium,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.End
                )
            }
        }
        Spacer(Modifier.height(5.dp))
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            placeholder = {
                Text(
                    text = hint,
                    color = TextHintColor,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.W400,
                        lineHeight = 18.sp,
                    )
                )
            },
            singleLine = singleLine,
            colors = colors,
            modifier = modifier.fillMaxWidth().focusRequester(focusRequester),
            shape = RoundedCornerShape(16.dp),
            maxLines = maxLines,
            interactionSource = interactionSource,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            trailingIcon = if (keyboardType == KeyboardType.Password) {
                { TrailingIcon(showPassword) { showPassword = !showPassword } }
            } else trailingIcon,
            visualTransformation = visualTransformation(keyboardType, showPassword),
            isError = isError
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
private fun containerColor(isError: Boolean, correctValidation: Boolean): Color {
    return if (isError) {
        MaterialTheme.colorScheme.errorContainer
    } else if (correctValidation) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onBackground
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
private fun visualTransformation(
    keyboardType: KeyboardType,
    showPassword: Boolean
): VisualTransformation {
    return if (showPassword || keyboardType != KeyboardType.Password && keyboardType != KeyboardType.NumberPassword) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewAppOutlinedTextField() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AppOutlinedTextField(
            hint = "Hello World",
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        AppOutlinedTextField(
            hint = "Hello World",
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Password,
            showLabel = true,
            label = "Password",
            moreInfoText = "Hello"
        )
    }
}