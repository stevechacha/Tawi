package com.freshtawi.tawi.presentation.screen.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.freshtawi.tawi.R
import com.freshtawi.tawi.presentation.screen.account.component.AccountComponent
import com.freshtawi.tawi.presentation.screen.account.component.AccountTopBar
import com.freshtawi.tawi.domain.model.AppSetting
import com.freshtawi.tawi.presentation.common.resources.Resources


@Composable
@Preview
fun AccountScreen(navController: NavController = rememberNavController()) {
    val securityTitle = Resources.strings.securityTitle
    val aboutUsTitle = Resources.strings.aboutUsTitle
    val changeLanguageTitle = Resources.strings.changeLanguageTitle
    val supportTitle = Resources.strings.supportTitle
    val accountItems = getAppSettingsItems()
    Scaffold(
        topBar = { AccountTopBar() }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(accountItems.size) { index ->
                    AccountComponent(
                        appSetting = accountItems[index],
                        onClick = { clickOption ->
                            when (clickOption) {
                                securityTitle -> navController.navigate(SECURITY_SCREEN_ROUTE)
                                aboutUsTitle -> navController.navigate(ABOUT_SCREEN_ROUTE)
                                changeLanguageTitle -> navController.navigate(CHANGE_LANGUAGE_ROUTE)
                                supportTitle -> navController.navigate(SUPPORT_SCREEN_ROUTE)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun getAppSettingsItems():List<AppSetting> {
    return listOf(
        AppSetting(
            title = Resources.strings.securityTitle,
            subTitle = Resources.strings.securitySubtitle,
            icon = R.drawable.settings_icon
        ),
        AppSetting(
            title = Resources.strings.changeLanguageTitle,
            subTitle = Resources.strings.changeLanguageSubTitle,
            icon = R.drawable.settings_icon
        ),
        AppSetting(
            title = Resources.strings.supportTitle,
            subTitle = Resources.strings.supportSubTitle,
            icon = R.drawable.settings_icon
        ),
        AppSetting(
            title = Resources.strings.aboutUsTitle,
            subTitle = Resources.strings.aboutUsSubTitle,
            icon = R.drawable.settings_icon
        )
    )
}


