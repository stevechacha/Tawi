package com.freshtawi.tawi.presentation.screen.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.presentation.common.composable.AppButton
import com.freshtawi.tawi.presentation.common.composable.AppOutlinedTextField
import com.freshtawi.tawi.presentation.common.resources.Resources
import com.freshtawi.tawi.presentation.screen.auth.welcome.component.RegisterOption
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainLoginScreen(
    onCreateAccountClick: () -> Unit = {},
    onNavigateToForgotPassword: () -> Unit = {},
    onClickNavigate: () -> Unit = {},
) {
    val loginViewModel: LoginViewModel = koinViewModel()
    val state by loginViewModel.state.collectAsState()

    val auth = Firebase.auth
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        loginViewModel.effect.collectLatest { effect ->
            when (effect) {
                is LoginScreenUIEffect.NavigateToHome -> onClickNavigate()
                LoginScreenUIEffect.NavigateToSignup -> onCreateAccountClick()
                LoginScreenUIEffect.NavigateToForgotPassword -> onNavigateToForgotPassword()
            }
        }

    }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().padding(16.dp).padding(paddingValues),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = Resources.strings.appName,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(top = 32.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.align(Alignment.CenterHorizontally).width(90.dp),
                    thickness = 2.dp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = Resources.strings.welcomeBack,
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(100.dp))

                Column(
                    modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        AppOutlinedTextField(
                            value = state.username,
                            onValueChange = loginViewModel::onUsernameChanged,
                            modifier = Modifier.fillMaxWidth(),
                            hint = Resources.strings.usernameHint,
                            errorMessage = if (state.isUsernameError) Resources.strings.invalidUsername else "",
                            isError = state.isUsernameError,
                        )
                        AppOutlinedTextField(
                            value = state.password,
                            onValueChange = loginViewModel::onPasswordChanged,
                            modifier = Modifier.fillMaxWidth(),
                            hint = Resources.strings.passwordHint,
                            showLabel = false,
                            errorMessage = if (state.isPasswordError) Resources.strings.invalidPassword else "",
                            isError = state.isPasswordError,
                            keyboardType = KeyboardType.Password
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = Resources.strings.createAccount,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W700,
                                lineHeight = 20.sp,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.clickable { onCreateAccountClick() }
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = Resources.strings.forgotPassword,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W700,
                                lineHeight = 18.sp,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.clickable { onNavigateToForgotPassword() }

                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))
                AppButton(
                    text = Resources.strings.login,
                    onClick = {
                        onClickNavigate()
                        /*loginViewModel.onClickLogin(
                            username = state.username,
                            password = state.password,
                            keepLoggedIn = state.keepLoggedIn,
                        )*/
                    }
                )
            }
        }
    }
}
