package com.freshtawi.tawi.presentation.screen.browse.browse.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil3.compose.AsyncImage
import com.freshtawi.tawi.domain.model.Product
import com.freshtawi.tawi.domain.model.ProductAvailable


@Composable
fun ProductHarvestSoonListItem(
    modifier: Modifier = Modifier,
    product: Product,
    onItemClick: (Product) -> Unit
) {
    ConstraintLayout(
        modifier = modifier.width(120.dp).height(154.dp)
            .clickable { onItemClick(product) }
            .border(
                BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(12.dp)
            ).padding(10.dp)
    ) {
        val (
            card,
            text1,
            text2,
            text3,
            text4
        ) = createRefs()
        Card(
            modifier = Modifier
                .constrainAs(card) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.value(105.dp)
                    height = Dimension.value(104.dp)
                }
                .padding(bottom = 8.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.productName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(105.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
            )
        }

        Text(
            text = "${product.productName }(${product.variety})",
            maxLines = 1,
            fontSize = 10.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 15.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(text1) {
                top.linkTo(card.bottom)
                start.linkTo(parent.start)
                end.linkTo(text2.start)
                width = Dimension.fillToConstraints
            }
        )

        Text(
            text = product.isReadyToSell.toString(),
            fontSize = 10.sp,
            lineHeight = 15.sp,
            textAlign = TextAlign.End,
            fontWeight = FontWeight.W700,
            color = when (product.isReadyToSell) {
               ProductAvailable.Ready -> { Color(0xFF33CA5D)}
               ProductAvailable.NotReady -> { Color(0xffEF2F2F)}
                else -> { Color(0xffEFDC2F) }
            },
            modifier = Modifier.constrainAs(text2) {
                top.linkTo(text1.top)
                end.linkTo(parent.end)
            },
        )

        Text(
            text = "${product.productPrice}/Tonne",
            fontSize = 10.sp,
            lineHeight = 15.sp,
            modifier = Modifier.constrainAs(text3) {
                top.linkTo(text2.bottom)
                start.linkTo(parent.start)
            }
        )

        Text(
            text = product.estimatedDate,
            fontSize = 10.sp,
            lineHeight = 15.sp,
            modifier = Modifier.padding(start = 8.dp)
                .constrainAs(text4) {
                    top.linkTo(text2.bottom)
                    end.linkTo(parent.end)
                }
        )
    }
}
