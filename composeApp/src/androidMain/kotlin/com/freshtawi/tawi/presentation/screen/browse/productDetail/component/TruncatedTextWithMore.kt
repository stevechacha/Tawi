package com.freshtawi.tawi.presentation.screen.browse.productDetail.component

import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TruncatedTextWithMore(text: String, maxLength: Int = 100) {
    var isExpanded by remember { mutableStateOf(false) }

    val displayText = if (isExpanded) {
        text
    } else {
        if (text.length > maxLength) {
            text.take(maxLength) + "..."
        } else {
            text
        }
    }

    Column(modifier = Modifier.padding(start = 20.dp)) {
        Text(
            text = buildAnnotatedString {
                append(displayText)
                if (!isExpanded && text.length > maxLength) {
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(" MORE")
                    }
                }
            },
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.clickable {
                if (!isExpanded) {
                    isExpanded = true
                }
            },
            textAlign = TextAlign.Start,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            fontSize = 12.sp,
            lineHeight = 18.sp,
        )
    }
}