package com.freshtawi.tawi.presentation.screen.auth.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.presentation.screen.auth.welcome.component.RegisterOption
import com.freshtawi.tawi.presentation.common.composable.VunaButton
import com.freshtawi.tawi.presentation.common.resources.Resources

@Composable
fun WelcomeBottom(
    onNavigateToLogin:()-> Unit = {},
    onNavigateToRegister: () -> Unit ={}
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        VunaButton(
            text = Resources.strings.login,
            enable = true,
            onClick = onNavigateToLogin
        )

        RegisterOption()

        VunaButton(
            text = Resources.strings.register,
            enable = true,
            onClick = onNavigateToRegister
        )

    }
}