package com.freshtawi.tawi.presentation.screen.farm.farmLanding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.freshtawi.tawi.R
import com.freshtawi.tawi.presentation.common.composable.BackgroundContainer
import com.freshtawi.tawi.presentation.screen.farm.FarmNavigationScreen
import com.freshtawi.tawi.presentation.screen.farm.components.FarmTopBar
import com.freshtawi.tawi.presentation.screen.farm.components.NewFarmCardComponent
import com.freshtawi.tawi.presentation.screen.farm.state.FarmViewModel
import com.freshtawi.tawi.presentation.common.resources.Resources


@Composable
fun FarmLandingScreen(
    navigator: NavController
) {

    val farmerViewModel = viewModel<FarmViewModel>()
    val farmerUiState by farmerViewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        farmerViewModel.getData()
    }


    BackgroundContainer(
        topBar = {
            FarmTopBar(
                title = Resources.strings.farmOverviewTitle,
                initialValue = "",
                onSearchParamChange = {},
                showSearchBar = true,
                showBackArrow = true,
                showMenuBar = true,
                searchHint = Resources.strings.farmSearchHint,
                actionIcon = R.drawable.ic_add_icon,
                onClickAction = {
                    navigator.navigate(FarmNavigationScreen.NewFarmDetail.route)
                }
            )

        }
    ) {
        Column(modifier = Modifier.padding()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                val farmSize = farmerUiState.farmList.size

                Text(
                    text = Resources.strings.total,
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = if (farmSize > 1) "$farmSize farms" else "$farmSize farm",
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = Resources.strings.filter,
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.End
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {

                    items(farmerUiState.farmList) { farm ->
                        NewFarmCardComponent(
                            onItemClick = {},
                            farm = farm
                        )
                    }
                }
            }

        }

    }
}

@Composable
@Preview
fun PreviewLandingOverView() {
    FarmLandingScreen(
        navigator = NavController(LocalContext.current)
    )

}


