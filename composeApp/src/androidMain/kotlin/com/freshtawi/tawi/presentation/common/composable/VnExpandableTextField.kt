package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.Dimen
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.TextHintColor
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.Poppins

@Composable
fun VnExpandableTextField(
    modifier: Modifier = Modifier,
    label: String = "",
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = "",
    keyboardType: KeyboardType = KeyboardType.Text
) {
    val colors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = Color(0xffD9D9D9),
        focusedBorderColor = Color.Transparent,
        unfocusedBorderColor = Color.Transparent,
    )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(bottom = Dimen.Space.main),
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Start
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 104.dp, max = 160.dp),
            value = value,
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
            onValueChange = onValueChange,
            shape = RoundedCornerShape(8.dp),
            singleLine = false,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = colors,
        )
    }

}