package com.freshtawi.tawi.presentation.screen.farm.addWateringSystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun AddWateringSystemScreen(navController: NavController) {
    Scaffold(
        topBar = {
            AppToolbar(
                onNavigateBack = {},
                title = Resources.strings.addWateringSystem,
                showBackArrow = true
            )
        },
        bottomBar = {
                AppButton(
                    text = Resources.strings.addButtonText,
                    onClick = {
                        navController.navigate(FarmNavigationScreen.FarmOverViewScreen.route)
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
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AppOutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    label = Resources.strings.waterSystemNameIdLabel,
                    hint = Resources.strings.waterSystemNameIdHint,

                )

                AddBoxText(
                    text = Resources.strings.productWatered,
                    onAddClick = {}

                    )

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(3) {
                        WateringSystemCardItem()
                    }


                }
            }


        }


    }

}

