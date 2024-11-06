
package com.freshtawi.tawi.presentation.screen.browse.productDetail.component


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.R
import com.freshtawi.tawi.presentation.common.composable.VunaRating
import com.freshtawi.tawi.domain.model.Product

@Composable
fun ProductReviewRatingItem(
    product: Product
) {
    val review = product.reviews
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().wrapContentHeight()
    ) {
        Text(
            text = "Reviews:",
            style = MaterialTheme.typography.labelMedium
        )
        Box {
            VunaRating(
                rating = product.averageRating,
                selectedIcon = painterResource(R.drawable.ic_filled_star_dark),
                halfSelectedIcon = painterResource(id = R.drawable.ic_half_filled_star_dark),
                iconsSize = 16.dp
            )
        }
        Text(
            text = if (review > 1) "$review Reviews" else "$review Review",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}