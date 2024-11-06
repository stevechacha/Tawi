package com.freshtawi.tawi.presentation.screen.farm.farm_overview.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.R
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.BottomBarColor

@Composable
@Preview
fun StatusCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = BottomBarColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(start = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
                Text(text = "Sunny , 100C")
                Text(text = "10: 00am EAT")
            }
            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    VerticalDivider(
                        thickness = 1.dp,
                        modifier = Modifier
                            .width(2.dp)
                            .height(120.dp)
                    )
                    Linear()
                }

            }

        }

    }
}

@Composable
fun Linear() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DataAnalysis(text = "Harvest:", value = 0.6f)
        DataAnalysis("Sprays:", 0.5f)
        DataAnalysis("Water: ", 0.8f)

    }

}

@Composable
fun DataAnalysis(
    text: String,
    value: Float
) {
    val numberOfDay = value * 7
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = text)
        Text(text = "${numberOfDay.toInt()} Days ")
    }
    LinearProgressIndicator(
        progress = { value },
    )

}