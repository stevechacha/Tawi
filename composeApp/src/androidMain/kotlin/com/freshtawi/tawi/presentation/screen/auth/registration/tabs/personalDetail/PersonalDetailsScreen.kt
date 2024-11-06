package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.personalDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.presentation.screen.auth.registration.RegistrationViewModel
import com.freshtawi.tawi.presentation.common.composable.AppButton
import com.freshtawi.tawi.presentation.common.composable.AppOutlinedTextField
import com.freshtawi.tawi.presentation.common.composable.designsystem.component.HeadFirstCard
import com.freshtawi.tawi.presentation.common.composable.HorizontalIndicator
import com.freshtawi.tawi.presentation.common.composable.VunaTecDropDown
import com.freshtawi.tawi.presentation.common.composable.getCounties
import com.freshtawi.tawi.presentation.common.resources.Resources
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PersonalDetailsScreen(
    onContinue: () -> Unit = {},
    farmerViewModel: RegistrationViewModel = koinViewModel()
) {
    val farmerState by farmerViewModel.state.collectAsState()

    /*LaunchedEffect(key1 = Unit) {
        farmerViewModel.effect.collectLatest { effect ->
            when (effect) {
                PersonalDetailsScreenEffect.NavigateBack -> {   }
                is PersonalDetailsScreenEffect.NavigateToFarmerDetailScreen -> onNavigateToLoginDetails(userType)
            }
        }
    }*/

    HeadFirstCard(
        textHeader = Resources.strings.tellUsAboutYou,
        textSubHeader = Resources.strings.personalDetail,
        pageIndicators = { HorizontalIndicator(3, currentPage = 2) },
        bottomBar = {
            AppButton(
                text = Resources.strings.continueHint,
                onClick = onContinue,
                enable = true
            )
        }
    ) {

        AppOutlinedTextField(
            value = farmerState.firstName,
            onValueChange = farmerViewModel::onFirstNameChanged,
            modifier = Modifier.fillMaxWidth(),
            hint = Resources.strings.firstName,
            label = Resources.strings.tellUsAboutYourName
        )
        Spacer(modifier = Modifier.height(12.dp))
        AppOutlinedTextField(
            value = farmerState.lastName,
            onValueChange = farmerViewModel::onLastNameChanged,
            modifier = Modifier.fillMaxWidth(),
            hint = Resources.strings.lastName,
            showLabel = false
        )

        Spacer(modifier = Modifier.height(16.dp))
        AppOutlinedTextField(
            value = farmerState.streetName,
            onValueChange = farmerViewModel::onStreetInformationChanged,
            modifier = Modifier.fillMaxWidth(),
            hint = Resources.strings.streetInformation,
            keyboardType = KeyboardType.Text,
            label = Resources.strings.tellUsAboutYourLocation,
            moreInfoText = Resources.strings.why
        )
        FarmerProvince(
            regionProvince = farmerState.province,
            zipCode = farmerState.zipCode,
            onProvinceChanged = farmerViewModel::onProvinceChanged,
            onZipCodeChanged = farmerViewModel::onZipCodeChanged
        )

        VunaTecDropDown(
            value = farmerState.county,
            onValueChanged = farmerViewModel::onCountyChanged,
            data = getCounties(),
            hint = Resources.strings.country
        )
    }
}



@Composable
private fun FarmerProvince(
    regionProvince: String,
    zipCode: String,
    onProvinceChanged: (String) -> Unit,
    onZipCodeChanged: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(.5f),
            verticalArrangement = Arrangement.Center
        ) {
            AppOutlinedTextField(
                value = regionProvince,
                onValueChange = onProvinceChanged,
                modifier = Modifier.fillMaxWidth(),
                hint = Resources.strings.regionProvince,
                keyboardType = KeyboardType.Text,
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            AppOutlinedTextField(
                value = zipCode,
                onValueChange = onZipCodeChanged,
                modifier = Modifier.fillMaxWidth(),
                hint = Resources.strings.zipCode,
                keyboardType = KeyboardType.Number,
            )
        }
    }

}






