package com.freshtawi.tawi.presentation.screen.farm.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.presentation.common.resources.Resources

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewFarmTopBar(
    title: String = "",
    showSearchBar: Boolean = true,
    showActionBar: Boolean = false,
    showBackArrow: Boolean = false,
    actionIcon: Int? = null,
    onClickAction: () -> Unit = {},
    showBarContent: @Composable () -> Unit = {},
    onNavigateBack: () -> Unit = {}
) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W700,
                        lineHeight = 30.sp,
                    )

                },
                navigationIcon = {
                    if (showBackArrow) {
                        IconButton(onClick = { onNavigateBack() }) {
                            Icon(
                                imageVector = Resources.images.arrowBack,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                },

                actions = {
                    if (showActionBar) {
                        IconButton(onClick = { onClickAction() }) {
                                Icon(
                                    imageVector = Resources.images.close,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
            AnimatedVisibility(visible = showSearchBar) {
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 4.dp)
                        .fillMaxWidth()
                        .height(54.dp)
                ) {
                    showBarContent()
                }
            }
        }
    }
}
