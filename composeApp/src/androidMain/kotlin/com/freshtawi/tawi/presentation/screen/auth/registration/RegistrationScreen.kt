package com.freshtawi.tawi.presentation.screen.auth.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.presentation.screen.auth.registration.RegistrationViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.freshtawi.tawi.presentation.screen.auth.registration.tabs.buyerDetail.BuyerDetailScreen
import com.freshtawi.tawi.presentation.screen.auth.registration.tabs.codeVerification.SmsVerificationScreen
import com.freshtawi.tawi.presentation.screen.auth.registration.tabs.codeVerification.SuccessVerificationScreen
import com.freshtawi.tawi.presentation.screen.auth.registration.tabs.farmerDetail.FarmerDetailsScreen
import com.freshtawi.tawi.presentation.screen.auth.registration.tabs.loginDetail.LoginDetailsScreen
import com.freshtawi.tawi.presentation.screen.auth.registration.tabs.personalDetail.PersonalDetailsScreen
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalPagerApi::class)
@Composable
fun RegistrationScreen(
    userType: String,
    onNavigateToLogin: () -> Unit = {},
    viewModel: RegistrationViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        HorizontalPager(
            count = 6,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> if (userType == "Buyer") {
                    BuyerDetailScreen(onContinue = { coroutineScope.launch { pagerState.animateScrollToPage(1) } })
                } else {
                    FarmerDetailsScreen(onContinue = { coroutineScope.launch { pagerState.animateScrollToPage(1) } })
                }
                1 -> PersonalDetailsScreen(onContinue = { coroutineScope.launch { pagerState.animateScrollToPage(2) } })
                2 -> LoginDetailsScreen(onContinue = { coroutineScope.launch { pagerState.animateScrollToPage(3) } })
                3 -> SmsVerificationScreen(onContinue = { coroutineScope.launch { pagerState.animateScrollToPage(4) } })
                4 -> SuccessVerificationScreen(onNavigateToLogin = onNavigateToLogin)
            }
        }
    }
}




