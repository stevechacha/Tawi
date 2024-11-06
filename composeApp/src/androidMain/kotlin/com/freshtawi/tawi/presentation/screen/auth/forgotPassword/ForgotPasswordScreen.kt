package com.freshtawi.tawi.presentation.screen.auth.forgotPassword

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.presentation.common.composable.AppButton
import com.freshtawi.tawi.presentation.common.composable.AppOutlinedTextField
import com.freshtawi.tawi.presentation.common.composable.AppToolbar
import com.freshtawi.tawi.presentation.common.composable.designsystem.component.HeadFirstCard

@Composable
fun ForgotPasswordScreen() {
    HeadFirstCard(
        textHeader = "Forgot Password?",
        textSubHeader = "Enter your email address to reset your password.",
        topAppBar = {
            AppToolbar(
                title = "Reset"
            ) },
        bottomBar = {
        }
    ) {
        AppOutlinedTextField(
            value = "",
            onValueChange = { /*TODO*/ },
        )
        Spacer(modifier = Modifier.height(20.dp))
        AppButton(text = "Reset") { }


    }

}