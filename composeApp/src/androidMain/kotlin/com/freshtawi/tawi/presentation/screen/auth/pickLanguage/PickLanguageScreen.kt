package com.freshtawi.tawi.presentation.screen.auth.pickLanguage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.freshtawi.tawi.presentation.screen.auth.pickLanguage.component.LanguageCard
import com.freshtawi.tawi.presentation.common.resources.Resources
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun PickLanguageScreen(
    pickLanguageViewModel: PickLanguageViewModel = koinViewModel(),
    navigateToAuth: (String)->Unit,
) {

    val state by pickLanguageViewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        pickLanguageViewModel.effect.collectLatest { effect ->
            when (effect) {
                is PickLanguageUIEffect.OnGoToPreferredLanguage -> navigateToAuth.invoke(state.languages.first().code)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            Text(
                text = Resources.strings.chooseYourPreferredLanguage,
                style = MaterialTheme.typography.headlineSmall
            )
            LazyVerticalGrid(
                contentPadding = PaddingValues(top = 16.dp),
                columns = GridCells.Adaptive(140.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.languages.size) { index ->
                    LanguageCard(
                        state = state.languages[index],
                        onClick = {
                            navigateToAuth.invoke(state.languages[index].code)
                            pickLanguageViewModel.onLanguageSelected(state.languages[index])
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun PickLanguageScreenContent(
    state: PickLanguageUIState,
    languageViewModel: PickLanguageViewModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            LazyVerticalGrid(
                contentPadding = PaddingValues(top = 16.dp),
                columns = GridCells.Adaptive(140.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.languages.size) { index ->
                    LanguageCard(
                        state = state.languages[index],
                        onClick = {
                            languageViewModel.onLanguageSelected(state.languages[index])
                        }
                    )
                }
            }
        }
    }
}
