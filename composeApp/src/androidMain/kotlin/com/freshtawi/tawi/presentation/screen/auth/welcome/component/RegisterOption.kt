package com.freshtawi.tawi.presentation.screen.auth.welcome.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.BottomBarColor
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.Poppins
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.TextHintColor
import com.freshtawi.tawi.presentation.common.resources.Resources

@Composable
fun RegisterOption(onContinue: () -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(1f).height(1.dp)
                .background(BottomBarColor)
        )
        Text(
            text = Resources.strings.or,
            modifier = Modifier.padding(12.dp),
            fontSize = 16.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Center,
            color = TextHintColor,
            lineHeight = 24.sp
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(BottomBarColor)
        )
    }

}