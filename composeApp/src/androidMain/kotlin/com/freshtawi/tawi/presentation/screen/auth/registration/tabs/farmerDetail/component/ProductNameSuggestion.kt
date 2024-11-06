package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.farmerDetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.BottomBarColor
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.Poppins
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.TextHintColor


val suggestedProductNameLists =
    arrayListOf(
        "Mango", "Grapefruit", "Apple", "Watermelon", "Pear", "Orange", "Banana", "Cherry"
    )

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun ProductNameSuggestion(
    onClickProduct: (String) -> Unit,
    suggestedProductNameList: List<String> = emptyList()
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        maxItemsInEachRow = 3

    ) {
        repeat(suggestedProductNameList.size) { productName ->
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(BottomBarColor)
                    .clickable { onClickProduct(suggestedProductNameList[productName]) }
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    ClickableText(
                        text = AnnotatedString(suggestedProductNameList[productName]),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W700,
                            fontFamily = Poppins,
                            lineHeight = 14.sp
                        ),
                        onClick = { onClickProduct(suggestedProductNameList[productName]) }
                    )

                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = TextHintColor
                    )

                }
            }
        }
    }
}


