package com.freshtawi.tawi.presentation.screen.farm.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun AddPhotoComponent(
    text: String = "",
    onAddClick: () -> Unit = {}
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(MutableInteractionSource(), null) {
                onAddClick()
            }

    ) {
        val (text, icon, divider) = createRefs()

        Text(
            text = "Add Photos",
            modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(icon.bottom)
            }
        )

        IconButton(
            onClick = { /*TODO*/ },
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.onBackground
            ),
            content = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp)
                )
            },
            modifier = Modifier.constrainAs(icon) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(divider) {
                    top.linkTo(icon.bottom)
                },
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }

}