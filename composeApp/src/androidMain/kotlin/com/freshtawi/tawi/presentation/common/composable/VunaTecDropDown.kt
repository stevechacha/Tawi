package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.compose.ui.unit.toSize
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.TextHintColor
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.hover
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.successContainer
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.Poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VunaTecDropDown(
    value: String,
    onValueChanged: (String) -> Unit,
    data: List<String>,
    hint: String = "",
    label: String = "",
    moreInfoText: String = "",
    showLabel: Boolean = false
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val interactionSource = remember { MutableInteractionSource() }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    if (interactionSource.collectIsPressedAsState().value)
        expanded = !expanded

    val density = LocalDensity.current.density
    val screenHeight = LocalDensity.current.run { LocalConfiguration.current.screenHeightDp.dp.toPx() }
    val popupHeight = data.size.coerceAtMost(5) * 48.dp.value.dp // Assuming each item is around 48dp in height

    val offsetY = with(density) {
        val availableHeight = screenHeight - (textFieldSize.height+(20.dp).value) // Extra padding
        if (popupHeight > availableHeight.dp) {
            -popupHeight + 1.dp // Move upward if there's not enough space downward
        } else {
            0.dp // Don't move if there's enough space downward
        }
    }

    Box() {
        AppOutlinedTextField(
            value = value,
            onValueChange = { onValueChanged(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .clickable { expanded = !expanded }
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            interactionSource = interactionSource,
            hint = hint,
            label = label,
            moreInfoText = moreInfoText,
            showLabel = showLabel
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .offset(y = offsetY)
        ) {
            data.forEach { county ->
                DropdownMenuItem(
                    text = {
                        Text(text = county)
                    },
                    onClick = {
                        onValueChanged(county)
                        expanded = false
                    }
                )
            }
        }
    }
}

fun getCounties(): List<String> {
    return listOf(
        "Alameda",
        "Alpine",
        "Amador",
        "Butte",
        "Calaveras",
        "Colusa",
        "Contra Costa",
        "Del Norte",
    )
}


@Composable
private fun DropDownOutlinedTextField(
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
    val colors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = containerColor(isError, correctValidation),
        focusedBorderColor = Color.Transparent,
        unfocusedBorderColor = Color.Transparent,
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
                    textAlign = TextAlign.Start
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
            shape = RoundedCornerShape(8.dp),
            maxLines = maxLines,
            trailingIcon = trailingIcon,
            interactionSource = interactionSource,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
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
        hover
    } else if (correctValidation) {
        successContainer
    } else {
        Color(0xffD9D9D9)
    }
}