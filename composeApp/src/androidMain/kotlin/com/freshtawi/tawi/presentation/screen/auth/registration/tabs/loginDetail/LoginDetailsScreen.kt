package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.loginDetail


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
import com.freshtawi.tawi.presentation.common.composable.designsystem.component.HeadFirstCard
import com.freshtawi.tawi.presentation.common.composable.AppButton
import com.freshtawi.tawi.presentation.common.composable.AppOutlinedTextField
import com.freshtawi.tawi.presentation.common.composable.HorizontalIndicator
import com.freshtawi.tawi.presentation.common.resources.Resources
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginDetailsScreen(
    onContinue: () -> Unit = {},
    viewModel: RegistrationViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()

   /* LaunchedEffect(key1 = true) {
        val account = viewModel.getUser(userType)
        viewModel.setUser(account)
    }*/

    HeadFirstCard(
        textHeader = Resources.strings.setUpAccount,
        textSubHeader = Resources.strings.loginDetails,
        pageIndicators = { HorizontalIndicator(3, currentPage = 3) },
        bottomBar = {
            AppButton(
                text = Resources.strings.register,
                onClick = onContinue,
                enable = true,
                bottomText = Resources.strings.termsAndConditions
            )
        }
    ) {

        AppOutlinedTextField(
            value = state.email,
            onValueChange = viewModel::onEmailChanged,
            modifier = Modifier.fillMaxWidth(),
            hint = Resources.strings.emailAddressHint,
            label = Resources.strings.enterYourEmailAddress,
            moreInfoText = Resources.strings.learnMore,
            errorMessage = if (state.isEmailError) Resources.strings.invalidEmail else "",
            isError = state.isEmailError,
        )

        Spacer(modifier = Modifier.height(12.dp))

        AppOutlinedTextField(
            value = state.phone,
            onValueChange = viewModel::onPhoneChanged,
            modifier = Modifier.fillMaxWidth(),
            hint = Resources.strings.phoneNumberHint,
            keyboardType = KeyboardType.Phone,
            label = Resources.strings.enterYourPhoneNumber,
            moreInfoText = Resources.strings.learnMore,
            errorMessage = if (state.isPhoneError) Resources.strings.invalidPhoneNumber else "",
            isError = state.isPhoneError,
        )
        Spacer(modifier = Modifier.height(12.dp))

        AppOutlinedTextField(
            value = state.password,
            onValueChange = viewModel::onPasswordChanged,
            modifier = Modifier.fillMaxWidth(),
            hint = Resources.strings.passwordHint,
            keyboardType = KeyboardType.Password,
            label = Resources.strings.enterYourPassword,
            moreInfoText = Resources.strings.learnMore,
            errorMessage = if (state.isPasswordError) Resources.strings.invalidPassword else "",
            isError = state.isPasswordError,
        )
        Spacer(modifier = Modifier.height(12.dp))

        AppOutlinedTextField(
            value = state.confirmPassword,
            onValueChange = viewModel::onConfirmPasswordChanged,
            modifier = Modifier.fillMaxWidth(),
            hint = Resources.strings.passwordHint,
            keyboardType = KeyboardType.Password,
            label = Resources.strings.reEnterYourPassword
        )
    }
}



