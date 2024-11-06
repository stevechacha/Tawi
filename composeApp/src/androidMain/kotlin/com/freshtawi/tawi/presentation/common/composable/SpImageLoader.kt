package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import com.seiko.imageloader.ImageRequestState
import com.seiko.imageloader.rememberAsyncImagePainter
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SpImageLoader(
    imageUrl: String,
    errorPlaceholderImageUrl: DrawableResource,
    modifier: Modifier = Modifier,
    showLoadingState: Boolean = true,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    placeHolderScale: ContentScale = ContentScale.Crop,
    painter: com.seiko.imageloader.AsyncImagePainter = rememberAsyncImagePainter(imageUrl),
) {

    when (painter.requestState) {
        is ImageRequestState.Loading ->{
                 AnimatedVisibility(showLoadingState) {
                     Box(modifier.fillMaxSize().clip(RoundedCornerShape(4.dp)))
                 }
             }

        is ImageRequestState.Failure -> {
            Image(
                modifier = modifier.fillMaxSize(),
                painter = painterResource(errorPlaceholderImageUrl),
                contentScale = placeHolderScale,
                contentDescription = contentDescription,
            )
        }

        is ImageRequestState.Success -> {
            Image(
                modifier = modifier.fillMaxSize(),
                painter = painter,
                contentScale = contentScale,
                contentDescription = contentDescription,
            )
        }
    }
}