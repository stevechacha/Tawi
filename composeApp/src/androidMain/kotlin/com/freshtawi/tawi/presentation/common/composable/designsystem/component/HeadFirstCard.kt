package com.freshtawi.tawi.presentation.common.composable.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.presentation.common.composable.util.getNavigationBarPadding
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
fun HeadFirstCard(
    modifier: Modifier = Modifier,
    textHeader: String = "",
    textSubHeader: String = "",
    topAppBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    pageIndicators: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = topAppBar,
        bottomBar = {
            Column(
                modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(getNavigationBarPadding())
            ) { bottomBar()
            }},
    ) { paddingValues ->
        Box(
            modifier = modifier.fillMaxSize().padding(paddingValues),
            contentAlignment = Alignment.TopStart
        ) {
            Column { pageIndicators() }
            Spacer(modifier = Modifier.height(8.dp))
            CardHeader(
                textHeader = textHeader,
                textSubHeader = textSubHeader,
                modifier = modifier
            )
            ContentScreen(
                modifier = modifier.align(Alignment.Center),
                content = content,
            )

        }
    }
}


@Composable
private fun ContentScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 30.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        content()
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun CardHeader(
    textHeader: String,
    textSubHeader: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = textHeader,
            fontSize = 20.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 30.sp
        )
        Text(
            text = textSubHeader,
            fontSize = 14.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 21.sp,
            color = Color.Black
        )

    }
}

@Composable
private fun BottomBarText(bottomText: String){
    Text(
        text = bottomText,
        textDecoration = TextDecoration.Underline,
        fontWeight = FontWeight.W700,
        lineHeight = 15.sp,
        fontSize = 10.sp,
    )
}