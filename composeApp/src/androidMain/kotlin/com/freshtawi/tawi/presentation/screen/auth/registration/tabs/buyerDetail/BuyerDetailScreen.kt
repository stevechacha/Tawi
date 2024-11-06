package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.buyerDetail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.freshtawi.tawi.presentation.screen.auth.registration.RegistrationViewModel
import com.freshtawi.tawi.presentation.common.composable.designsystem.component.HeadFirstCard
import com.freshtawi.tawi.presentation.common.composable.AppButton
import com.freshtawi.tawi.presentation.common.composable.AppOutlinedTextField
import com.freshtawi.tawi.presentation.common.composable.HorizontalIndicator
import com.freshtawi.tawi.presentation.common.composable.VunaTecDropDown
import com.freshtawi.tawi.presentation.common.composable.getCounties
import com.freshtawi.tawi.presentation.common.resources.Resources
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun BuyerDetailScreen(
    onContinue: () -> Unit = {},
    buyerDetailViewModel: RegistrationViewModel = koinViewModel(),
) {
    val state by buyerDetailViewModel.state.collectAsState()


    HeadFirstCard(
        textHeader = Resources.strings.tellUsAboutYou,
        textSubHeader = Resources.strings.buyerDetails,
        bottomBar = {
            AppButton(
                text = Resources.strings.continueHint,
                onClick = onContinue,
                enable = true
            )
        },
        pageIndicators = {
            HorizontalIndicator(
                pageCount = 3,
                currentPage = 1
            )
        }
    ) {
        AppOutlinedTextField(
            value = state.buyerCompanyName,
            onValueChange = buyerDetailViewModel::setCompanyName,
            modifier = Modifier.fillMaxWidth(),
            hint = Resources.strings.buyerCompanyNameHint,
            label = Resources.strings.buyerNameCompanyQn,
        )
        VunaTecDropDown(
            value = state.buyerType,
            onValueChanged = buyerDetailViewModel::setBuyerType,
            data = getCounties(),
            hint = Resources.strings.buyerCompanyDoHint,
            label = Resources.strings.buyerCompanyDoQn,
            moreInfoText = Resources.strings.why,
            showLabel = true
        )
        VunaTecDropDown(
            value = state.buyerReceiveProductModel,
            onValueChanged = buyerDetailViewModel::setBuyerReceiveProductModel,
            data = getCounties(),
            hint = Resources.strings.buyerPickUpOrOrganized,
            label = Resources.strings.buyerShipReceiveMeanQn,
            showLabel = true
        )
    }
}
