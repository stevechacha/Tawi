package com.freshtawi.tawi.presentation.screen.auth.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.R
import com.freshtawi.tawi.presentation.common.composable.AppToolbar
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.Poppins
import com.freshtawi.tawi.presentation.common.composable.designsystem.component.HeadFirstCard
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.TextHintColor
import com.freshtawi.tawi.presentation.common.resources.Resources

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    code: String,
    onNavigateToLogin:()-> Unit = {},
    onNavigateToRegister: () -> Unit ={}
) {
    HeadFirstCard(
        topAppBar = {
            AppToolbar(
                title = Resources.strings.appName,
                style = TextStyle(
                    fontWeight = FontWeight.W400,
                    fontSize = 30.sp,
                    lineHeight = 45.sp,
                    fontFamily = Poppins,
                )
            )
        },
        bottomBar = {
            WelcomeBottom(
                onNavigateToLogin = onNavigateToLogin,
                onNavigateToRegister = onNavigateToRegister
            )
        }
    ) {
        Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.icon_image),
//                    contentDescription = null,
//                    modifier = Modifier.size(200.dp),
//                    tint = MaterialTheme.colorScheme.primary
//                )
                Text(
                    text = Resources.strings.welcomeMessage,
                    fontSize = 36.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center,
                    color = TextHintColor,
                    lineHeight = 54.sp
                )
                Text(
                    text = Resources.strings.getStarted,
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center,
                    color = TextHintColor

                )
            }

    }

}

