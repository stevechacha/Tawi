package com.tecvuna.core.designsystem.resources.images

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.freshtawi.tawi.presentation.common.resources.Resources

@Composable
fun ImageLoaderImage() {
    Image(
        painter = painterResource(id = Resources.images.filledStar),
        contentDescription = null )

}