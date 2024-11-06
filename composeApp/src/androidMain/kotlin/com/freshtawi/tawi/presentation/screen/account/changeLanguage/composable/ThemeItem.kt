package com.freshtawi.tawi.presentation.screen.account.changeLanguage.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ThemeItem(
    languageName: String,
    onSelectTheme: (String) -> Unit,
    isSelected: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onSelectTheme(languageName) },
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onSelectTheme(languageName) },
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium)
            ),
        )
        Text(
            text = languageName,
            style = MaterialTheme.typography.labelMedium
        )
    }
}