package com.freshtawi.tawi.presentation.screen.account.changeLanguage

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.freshtawi.tawi.presentation.screen.account.changeLanguage.composable.ChangeLanguageCard
import com.freshtawi.tawi.presentation.common.composable.AppToolbar
import com.freshtawi.tawi.presentation.common.resources.Resources
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ChangeLanguageScreen(
    viewModel: ChangeLanguageViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val selectedLanguage by viewModel.language.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            AppToolbar(
                title = Resources.strings.changeLanguageTitle,
                showBackArrow = true
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            val languagesLocal = state.languages

            Image(
                painter = painterResource( id = Resources.images.vunaIcon),
                contentDescription = null
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(state.languages){ language ->
                    ChangeLanguageCard(
                        language = language ,
                        onLanguageSelected = viewModel::onLanguageSelected,
                        selected = language.code == selectedLanguage.value
                    )
                }
            }

        }
    }

}