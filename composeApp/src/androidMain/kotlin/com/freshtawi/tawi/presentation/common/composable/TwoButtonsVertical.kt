package com.freshtawi.tawi.presentation.common.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.BottomBarColor

@Composable
fun TwoButtonsVertical(
    topButtonText: String,
    bottomButtonText: String,
    onTopButtonClick:() -> Unit,
    onBottomButtonClick:() -> Unit,
    modifier: Modifier = Modifier,
    enableTopButton: Boolean = false,
    enableBottomButton: Boolean = false,
    showTopButton: Boolean = false
) {
    Column(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp,
                    bottom = 20.dp
                )
        ) {
            if (showTopButton){
                Button(
                    onClick = { onTopButtonClick() },
                    shape = RoundedCornerShape(8.dp),
                    enabled = enableTopButton,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (enableTopButton) BottomBarColor else BottomBarColor

                    )
                ) {
                    Text(
                        text = topButtonText.uppercase(),
                        modifier = Modifier.padding(8.dp),
                        fontWeight = FontWeight.W700,
                        fontSize = 16.sp,
                        lineHeight = 24.sp
                    )
                }
            }

            Button(
                onClick = { onBottomButtonClick() },
                shape = RoundedCornerShape(8.dp),
                enabled = enableBottomButton,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (enableBottomButton) BottomBarColor else BottomBarColor

                )
            ) {
                Text(
                    text = bottomButtonText.uppercase(),
                    modifier = Modifier.padding(8.dp),
                    fontWeight = FontWeight.W700,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
            }
        }
    }
}