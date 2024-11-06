package com.freshtawi.tawi.presentation.screen.alerts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.R
import com.tecvuna.alerts.composable.NotificationCard
import com.freshtawi.tawi.presentation.common.composable.ContentVisibility
import com.freshtawi.tawi.presentation.common.composable.LoginRequiredPlaceholder
import com.freshtawi.tawi.presentation.common.resources.Resources
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlertsScreen(
    notificationScreenModel: NotificationScreenModel = koinViewModel(),
    navigateToLogin:()-> Unit = {}
) {
    val state by notificationScreenModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        notificationScreenModel.effect.collectLatest { effect ->
            when (effect) {
                is NotificationUiEffect.MakeOrderAgain -> println("order again")
                is NotificationUiEffect.NavigateToTraceOrderScreen -> println("navigate to trace order screen")
                NotificationUiEffect.NavigateToLoginScreen -> navigateToLogin()
                else -> Unit

            }

        }
    }


    Scaffold { paddingValues ->
        Column (
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            LoginRequiredPlaceholder(
                placeHolder = painterResource(R.drawable.ic_unstart),
                message = Resources.strings.notificationLoginMessage,
                onClickLogin = notificationScreenModel::onClickLogin
            )
            ContentVisibility(state.isLoggedIn) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentPadding = PaddingValues(vertical = 24.dp)
                ) {
                    itemsIndexed(state.todayNotifications) { index, item ->
                        val showDate = index == 0
                        NotificationCard(
                            title = item.title,
                            showDate = showDate,
                            date = Resources.strings.today,
                            time = item.time,
                            content = item.body,
                            isClickable = true,
                            clickableText = Resources.strings.tryAgain
                        )
                    }

                    itemsIndexed(state.thisWeekNotifications) { index, item ->
                        if (index == 0) {
                            NotificationCard(
                                title = item.title,
                                showDate = true,
                                date = Resources.strings.thisWeek,
                                time = item.time,
                                content = item.body,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
                                isClickable = true,
                                clickableText = Resources.strings.trackYourOrder
                            )
                        } else {
                            NotificationCard(title = item.title, time = item.time, content = item.body)
                        }
                    }
                }
            }
        }
    }

}