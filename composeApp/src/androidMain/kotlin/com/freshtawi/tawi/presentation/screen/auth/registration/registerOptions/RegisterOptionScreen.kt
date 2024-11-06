package com.freshtawi.tawi.presentation.screen.auth.registration.registerOptions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.freshtawi.tawi.presentation.screen.auth.welcome.component.RegisterButtonOption
import com.freshtawi.tawi.presentation.common.composable.designsystem.component.HeadFirstCard
import com.freshtawi.tawi.presentation.common.composable.HorizontalIndicator
import com.freshtawi.tawi.presentation.common.resources.Resources


@Composable
fun RegisterOptionScreen(
    navigateToFarmerRegisterDetail: (String) -> Unit = {},
    navigateToBuyerRegisterDetail: (String) -> Unit = {},
    registerOptionViewModel: RegisterOptionViewModel = viewModel()
) {
    val state by registerOptionViewModel.uiState.collectAsState()
    val farmerId = state.farmer
    val buyerId = state.buyer

    HeadFirstCard(
        textHeader = Resources.strings.createNewAccountHint,
        textSubHeader = Resources.strings.accountStatus,
        pageIndicators = { HorizontalIndicator(currentPage = 0) },
    ) {
        RegisterOptionContent(
            navigateToFarmerRegisterDetail = {
                registerOptionViewModel.onFarmerSelected(it)
                navigateToFarmerRegisterDetail.invoke(farmerId)
            },
            navigateToBuyerRegisterDetail = {
                registerOptionViewModel.onBuyerSelected(it)
                navigateToBuyerRegisterDetail.invoke(buyerId)
            }
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = Resources.strings.alreadyHaveAnAccount,
            fontSize = 10.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 15.sp,
            textDecoration = TextDecoration.Underline,
        )
    }
}

@Composable
fun RegisterOptionContent(
    navigateToFarmerRegisterDetail: (String) -> Unit,
    navigateToBuyerRegisterDetail: (String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = Resources.strings.signUpOptionHeader,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 20.sp,
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = Resources.strings.learnMore,
                fontSize = 12.sp,
                fontWeight = FontWeight.W700,
                lineHeight = 18.sp,
                textAlign = TextAlign.Right,
                textDecoration = TextDecoration.Underline
            )
        }

        RegisterButtonOption(
            text = Resources.strings.farmer,
            enable = true,
            onClick = navigateToFarmerRegisterDetail
        )
        Spacer(modifier = Modifier.height(16.dp))
        RegisterButtonOption(
            text = Resources.strings.buyer,
            onClick = navigateToBuyerRegisterDetail,
            enable = true,
        )
    }

}

