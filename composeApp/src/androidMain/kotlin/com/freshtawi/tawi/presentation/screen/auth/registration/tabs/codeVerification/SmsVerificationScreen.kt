package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.codeVerification


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.presentation.screen.auth.registration.tabs.codeVerification.component.OTPInputField
import com.freshtawi.tawi.presentation.common.composable.designsystem.component.HeadFirstCard
import com.freshtawi.tawi.presentation.common.composable.HorizontalIndicator
import com.freshtawi.tawi.presentation.common.composable.OtpTextField
import com.freshtawi.tawi.presentation.common.composable.TwoButtonsVertical
import com.freshtawi.tawi.presentation.common.resources.Resources
import com.freshtawi.tawi.presentation.screen.auth.otp.OtpScreen


@SuppressLint("UnrememberedMutableState", "NewApi")
@Composable
fun SmsVerificationScreen(
    onContinue: () -> Unit = {},
    userType: String = "",
    onNavigateToSuccessVerification: (String) -> Unit = {},
) {
    val otpInputs = List(6) { remember { mutableStateOf("") } }
    val otpValue by remember { mutableStateOf("") }
    val enableButton = derivedStateOf {
        otpValue.length == 6
    }

    HeadFirstCard(
        textHeader = Resources.strings.oneLastStep,
        textSubHeader = Resources.strings.smsVerification,
        pageIndicators = { HorizontalIndicator(3, currentPage = 3) },
        bottomBar = {
            TwoButtonsVertical(
                topButtonText = Resources.strings.verify,
                bottomButtonText = Resources.strings.resendCode,
                onTopButtonClick = onContinue,
                onBottomButtonClick = { /*TODO*/ },
                enableTopButton = true,
                enableBottomButton = enableButton.value,
                showTopButton = true
            )
        }
    ) {
        Text(
            text = Resources.strings.enterCode,
            fontWeight = FontWeight.W400,
            color = Color.Black,
            fontSize = 13.sp,
            lineHeight = 20.sp
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            otpInputs.forEachIndexed { index, input ->
                OtpTextField(
                    inputValue = input,
                    onNextFocus = if (index < otpInputs.size - 1) otpInputs[index + 1] else null
                )
            }
        }
        //        OTPInputField(
//            otpLength = 6,
//            onOtpChange = {},
//            onNavigateToSuccessVerification = { onNavigateToSuccessVerification(userType) }
//        )

        Text(
            text = Resources.strings.whereToFindVerificationCode,
            fontWeight = FontWeight.W700,
            color = Color.Black,
            fontSize = 10.sp,
            lineHeight = 15.sp,
            textDecoration = TextDecoration.Underline
        )

    }

}




