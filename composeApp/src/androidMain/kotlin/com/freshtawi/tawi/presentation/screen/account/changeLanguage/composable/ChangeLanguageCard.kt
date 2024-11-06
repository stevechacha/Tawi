package com.freshtawi.tawi.presentation.screen.account.changeLanguage.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.presentation.screen.account.changeLanguage.LanguageUIState
@Composable
fun ChangeLanguageCard(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    language: LanguageUIState,
    onLanguageSelected: (LanguageUIState) -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = { onLanguageSelected(language) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selected,
                onClick = { onLanguageSelected(language) }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = language.name,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = language.name,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
