package com.freshtawi.tawi.presentation.screen.auth.pickLanguage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.freshtawi.tawi.presentation.screen.auth.pickLanguage.LanguageUIState

@Composable
fun LanguageCard(onClick: () -> Unit, state: LanguageUIState) {
    Box(
        modifier = Modifier
            .height(88.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
          AsyncImage(
              model = ImageRequest.Builder(LocalContext.current)
                  .data(state.image).build(),
              contentDescription = "${state.name} Picture",
              contentScale = ContentScale.FillBounds,
              modifier = Modifier
                  .clip(MaterialTheme.shapes.large)
          )

        /*Image(
            painter = org.jetbrains.compose.resources.painterResource(state.image),
            contentDescription = "${state.name} Picture",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.clip(MaterialTheme.shapes.large)
        )*/

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.Black.copy(alpha = 0.4f),
                    shape = MaterialTheme.shapes.large
                )
        )
        Text(
            text = state.name,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}