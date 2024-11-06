package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.material3.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.presentation.common.resources.Resources
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.Poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    onNavigateBack: () -> Unit = {},
    title: String,
    showBackArrow: Boolean = false,
    action: @Composable () -> Unit = {},
    navigationAction: () -> Unit = {},
    style: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.W700,
        fontFamily = Poppins,
        lineHeight = 30.sp,
    )
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = style
            )
        },
        navigationIcon = {
            IconButton(onClick = { onNavigateBack() }) {
                if (showBackArrow) {
                    Icon(
                        imageVector = Resources.images.arrowBack,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
        ),
        actions = {
            action()
        },
    )

}