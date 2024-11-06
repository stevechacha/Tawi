package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import com.freshtawi.tawi.presentation.common.composable.modifier.noRippleEffect

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(
    images: List<String>,
    onItemClickListener: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
    indicatorColor: Color = MaterialTheme.colorScheme.primary,
    indicatorAlignment: Alignment = Alignment.BottomEnd,
    autoScrollDelayMillis: Long = 3000L, // delay between automatic page changes
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { images.size },
    )

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(autoScrollDelayMillis)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (if(pagerState.pageCount == 0) 1 else pagerState.pageCount ),
                animationSpec = tween(1000)
            )
        }
    }


    Box(
        modifier = modifier.fillMaxWidth().height(160.dp).clip(RoundedCornerShape(8.dp))
            .noRippleEffect { onItemClickListener(pagerState.currentPage) }
    ) {

       /* HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(0.dp),
            pageSpacing = 0.dp,
        ) { page ->
            SpImageLoader(
                imageUrl = images[page],
                errorPlaceholderImageUrl = Resources.images.cutie,
            )
        }*/

        ImageSliderIndicator(
            itemCount = images.size,
            pagerState = pagerState,
            indicatorColor = indicatorColor,
            indicatorAlignment = indicatorAlignment
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoxScope.ImageSliderIndicator(
    itemCount: Int,
    pagerState: PagerState,
    indicatorColor: Color = MaterialTheme.colorScheme.primary,
    indicatorAlignment: Alignment,
) {
    val circleOffsetList = remember { mutableStateListOf(Offset(0f, 0f)) }

    val animateOffset by animateOffsetAsState(
        targetValue = circleOffsetList.getOrElse(pagerState.currentPage) { Offset(0f, 0f) },
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = "indicator"
    )

    Row(
        Modifier.align(indicatorAlignment).padding(vertical = 8.dp, horizontal = 16.dp)
            .drawBehind {
                drawCircle(
                    color = indicatorColor,
                    center = Offset(animateOffset.x + 3.dp.toPx(), size.height / 2),
                    radius = 3.dp.toPx()
                )
            }
    ) {
        repeat(itemCount) { index ->
            Canvas(
                modifier = Modifier.padding(4.dp).size(6.dp)
                    .onGloballyPositioned { coordinates ->
                        val offset = Offset(
                            x = coordinates.positionInParent().x,
                            y = coordinates.positionInParent().y
                        )
                        if (circleOffsetList.size <= index) {
                            circleOffsetList.add(offset)
                        } else {
                            circleOffsetList[index] = offset
                        }
                    },
                onDraw = {
                    drawCircle(color = indicatorColor, style = Stroke(1.dp.toPx()))
                })
        }
    }
}