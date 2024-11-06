package com.freshtawi.tawi.presentation.screen.browse.productDetail.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailTopBar(
    title: String,
    showBackArrow: Boolean = true,
    showShoppingCart: Boolean = true,
    onBackClick: () -> Unit ,
    onClickActionButton:()->Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.W700,
                color = MaterialTheme.colorScheme.onBackground,
                lineHeight = 30.sp
            )
        },
        navigationIcon = {
            if (showBackArrow) {
                IconButton(
                    onClick = { onBackClick()},
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onBackground
                    ),
                    content = {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                )
            }
        },
        actions = {
            if (showShoppingCart){
                IconButton(
                    onClick = { onClickActionButton()},
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onBackground
                    ),
                    content = {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingCart,
                            contentDescription = "Shopping"
                        )
                    }
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )

    )
}

