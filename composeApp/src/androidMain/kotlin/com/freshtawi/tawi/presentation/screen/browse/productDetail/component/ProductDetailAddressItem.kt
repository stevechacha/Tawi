package com.freshtawi.tawi.presentation.screen.browse.productDetail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.EmojiSupportMatch
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.domain.model.Product

@Composable
fun ProductDetailAddressItem(
    modifier: Modifier = Modifier,
    product: Product
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Sold by:",
            fontSize = 14.sp
        )
        Column(
            modifier = Modifier.align(Alignment.CenterVertically).padding(start = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            LocationItem(
                farmNameHolder = product.farmNameHolder,
                farmerProvince = product.farmerProvince,
                farmerCounty = product.farmerCounty,
                farmerDistance = product.farmerDistance.toString(),
                farmerProductAddress = product.farmerProductAddress
            )
        }
    }
}

@Composable
fun LocationItem(
    modifier: Modifier = Modifier,
    farmNameHolder: String = "",
    farmerProvince: String = "",
    farmerCounty: String = "",
    farmerDistance: String = "",
    farmerProductAddress: String = ""
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                append(farmNameHolder)
                appendInlineContent("inlineContent", "[call]")
                append("\t\t\t")
            },
            inlineContent = mapOf(
                Pair(
                    "inlineContent",
                    InlineTextContent(
                        Placeholder(
                            width = 16.sp,
                            height = 16.sp,
                            placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                        )
                    ){
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                )
            ),
            style = MaterialTheme.typography.bodyMedium,
            )
        Text(
            text = buildAnnotatedString {
                appendInlineContent("inlineContent", "[location]")
                append("${farmerProductAddress}\n")
                append("${farmerProvince},${farmerCounty} ($farmerDistance km )" )
            },
            inlineContent = mapOf(
                Pair(
                    "inlineContent",
                    InlineTextContent(
                        Placeholder(
                            width = 16.sp,
                            height = 16.sp,
                            placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                        )
                    ){
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                )
            ),
            style = MaterialTheme.typography.bodyMedium)
    }

}

@Preview
@Composable
fun EmojiSupportMatchNone() {
    Text(
        text = "Hello \uD83D\uDE00",
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                emojiSupportMatch = EmojiSupportMatch.None
            )
        )
    )
}