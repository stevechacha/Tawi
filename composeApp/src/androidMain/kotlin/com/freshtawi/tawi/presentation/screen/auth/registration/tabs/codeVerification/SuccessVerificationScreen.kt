package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.codeVerification

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.R
import com.freshtawi.tawi.presentation.common.composable.AppButton
import com.freshtawi.tawi.presentation.common.composable.designsystem.component.HeadFirstCard
import com.freshtawi.tawi.presentation.common.composable.HorizontalIndicator
import com.freshtawi.tawi.presentation.common.resources.Resources
import com.freshtawi.tawi.presentation.screen.auth.registration.RegistrationViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun SuccessVerificationScreen(
    onNavigateToLogin: () -> Unit = {},
    viewModel: RegistrationViewModel = koinViewModel()
) {
    HeadFirstCard(
        textHeader = Resources.strings.successVerificationMessage,
        textSubHeader = Resources.strings.welcomeNote,
        pageIndicators = { HorizontalIndicator(pageCount = 3, currentPage = 3) },
        bottomBar = {
            AppButton(
                text = Resources.strings.done,
                onClick = onNavigateToLogin,
                enable = true
            )
        }
    ) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_image),
                contentDescription = Resources.strings.successVerificationMessage,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier.fillMaxSize()
            )
        }

    }
}
