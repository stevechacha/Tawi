package com.freshtawi.tawi.presentation.screen.farm.addPesticide

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.freshtawi.tawi.presentation.common.composable.AppButton
import com.freshtawi.tawi.presentation.common.composable.AppOutlinedTextField
import com.freshtawi.tawi.presentation.common.composable.AppToolbar
import com.freshtawi.tawi.presentation.screen.farm.FarmNavigationScreen
import com.freshtawi.tawi.presentation.screen.farm.components.AddBoxText
import com.freshtawi.tawi.presentation.common.resources.Resources

@Composable
fun AddPesticideScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            AppToolbar(
                title = Resources.strings.addPesticideTitle,
                showBackArrow = true,
                onNavigateBack = {}
            )
        },
        bottomBar = {
            AppButton(
                text = Resources.strings.addButtonText,
                onClick = {
                    navController.navigate(FarmNavigationScreen.AddWateringSystem.route)
                }
            )


        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()

            ) {
                Column {
                    AppOutlinedTextField(
                        value = "",
                        onValueChange = {},
                        hint = Resources.strings.pesticideNameIdHint,
                        modifier = Modifier.fillMaxWidth(),
                        label = Resources.strings.pesticideNameIdLabel,

                        )
                    Spacer(modifier = Modifier.height(12.dp))
                    AddBoxText(
                        text = Resources.strings.productSprayed,
                        onAddClick = {},
                    )
                }

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(10) {
                        PesticideCardItem()
                    }

                    item {
                        Spacer(modifier = Modifier.height(12.dp))
                    }

                }
            }


        }

    }

}


data class Pesticide(
    val name: String,
    val date: String,
    val time: String
)

data class PesticideState(
    val name: String = "",
    val date: String = "",
    val time: String = ""
)

sealed class PesticideEvent {
    data class OnNameChanged(val name: String) : PesticideEvent()
    data class OnDateChanged(val date: String) : PesticideEvent()
    data class OnTimeChanged(val time: String) : PesticideEvent()
    object OnAddClick : PesticideEvent()
}

