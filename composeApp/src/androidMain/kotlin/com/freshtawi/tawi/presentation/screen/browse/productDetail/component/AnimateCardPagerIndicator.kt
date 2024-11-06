package com.freshtawi.tawi.presentation.screen.browse.productDetail.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.freshtawi.tawi.presentation.screen.browse.productList
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.ColorBorder
import com.freshtawi.tawi.domain.model.Product
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AnimatedCardPagerIndicator(products: List<Product>) {
    val pagerState = rememberPagerState(initialPage = 0)
    val cardCount = productList
    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            val nextPage = (pagerState.currentPage + 1) % products.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(ColorBorder)
    ) {
        HorizontalPager(
            count = cardCount.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            AnimatedCardItemIndicator(products[page])
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun AnimatedCardItemIndicator(product: Product) {
    var isVisible by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = isVisible, label = "cardTransition")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .animateContentSize()
            .clickable { isVisible = !isVisible },
        colors = CardDefaults.outlinedCardColors(
            containerColor = ColorBorder,
        ),
    ) {
        AsyncImage(
            model = product.image,
            contentDescription = product.productName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }

    LaunchedEffect(key1 = product) {
        isVisible = true
    }
}




