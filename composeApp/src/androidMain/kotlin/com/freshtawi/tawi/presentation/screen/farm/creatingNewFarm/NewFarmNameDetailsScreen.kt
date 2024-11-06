package com.freshtawi.tawi.presentation.screen.farm.creatingNewFarm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.freshtawi.tawi.presentation.common.composable.AppOutlinedTextField
import com.freshtawi.tawi.presentation.common.composable.VunaTecDropDown
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.TextHintColor
import com.freshtawi.tawi.presentation.common.composable.AppButton
import com.freshtawi.tawi.presentation.common.resources.Resources
import com.freshtawi.tawi.presentation.screen.farm.FarmNavigationScreen
import com.freshtawi.tawi.presentation.screen.farm.components.AddBoxText
import com.freshtawi.tawi.presentation.screen.farm.components.FarmProvince
import com.freshtawi.tawi.presentation.screen.farm.components.NewFarmHeaderFirst
import com.freshtawi.tawi.presentation.screen.farm.components.NewFarmTopBar
import com.freshtawi.tawi.presentation.screen.farm.components.getCounties
import com.freshtawi.tawi.presentation.screen.farm.state.FarmEvent
import com.freshtawi.tawi.presentation.screen.farm.state.FarmViewModel

@Composable
fun NewFarmNameDetailsScreen(
    navController: NavController
) {
    val farmViewModel: FarmViewModel = viewModel()
    val farmState by farmViewModel.state.collectAsState()
    val counties = getCounties()


    NewFarmHeaderFirst(
        topAppBar = {
            NewFarmTopBar(
                title = Resources.strings.newFarmTitle,
                showBarContent = { HorizontalIndicator(pageCount = 3, currentPage = 3) },
                showActionBar = true,
                showSearchBar = true,
                showBackArrow = true
            )
        },
        bottomBar = {
            AppButton(
                text = Resources.strings.continueHint,
                onClick = { navController.navigate(FarmNavigationScreen.NewFarmProductDetail.route) }
            )
        }
    ) {

        AppOutlinedTextField(
            value = farmState.firstName,
            onValueChange = { farmViewModel.onEvent(FarmEvent.OnFirstNameChanged(it)) },
            modifier = Modifier.fillMaxWidth(),
            hint = Resources.strings.firstName,
            label = Resources.strings.farmNameLabel
        )
        AppOutlinedTextField(
            value = farmState.lastName,
            onValueChange = { farmViewModel.onEvent(FarmEvent.OnLastNameChanged(it)) },
            modifier = Modifier.fillMaxWidth(),
            hint = Resources.strings.lastName,
            showLabel = false
        )

        AppOutlinedTextField(
            value = farmState.streetName,
            onValueChange = { farmViewModel.onEvent(FarmEvent.OnStreetNameChanged(it)) },
            modifier = Modifier.fillMaxWidth(),
            hint = Resources.strings.farmNameHint,
            label = Resources.strings.farmAddressLabel,
        )

        FarmProvince(
            province = farmState.province,
            zipCode = farmState.zipCode,
            onProvinceChanged = { farmViewModel.onEvent(FarmEvent.OnProvinceChanged(it)) },
            onZipCodeChanged = { farmViewModel.onEvent(FarmEvent.OnZipCodeChanged(it)) }
        )

        VunaTecDropDown(
            value = farmState.county,
            onValueChanged = { farmViewModel.onEvent(FarmEvent.OnCountyChanged(it))},
            data = counties
        )

        AppOutlinedTextField(
            value = farmState.farmSize,
            onValueChange = { farmViewModel.onEvent(FarmEvent.onFarmSizeChanged(it)) },
            modifier = Modifier.fillMaxWidth(),
            hint = Resources.strings.farmSizeLabelHint,
            label = Resources.strings.farmSizeLabel,
        )
        AddBoxText(
            onAddClick = {},
            text = Resources.strings.photos
        )
    }

}



@Composable
private fun HorizontalIndicator(currentPage: Int, pageCount: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { pageIndex ->
            val isCurrentPage = pageIndex == currentPage
            val indicatorColor = if (isCurrentPage) {
                MaterialTheme.colorScheme.onBackground
            } else {
                Color.LightGray

            }

            if (pageIndex > 0) {
                // Add one line between indicators for all except the first one
                Box(
                    modifier = Modifier
                        .width(95.dp)
                        .align(Alignment.CenterVertically)
                        .height(3.dp)
                        .padding(horizontal = 2.dp)
                        .background(indicatorColor)
                )
            }

            if (pageIndex == 0) {
                // Only show the text and title for the first index
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "1",
                            color = Color.White,
                            fontWeight = FontWeight.W700,
                            fontSize = 8.sp,
                            lineHeight = 12.sp,
                            textAlign = TextAlign.Center
                        )
                    }


                    Text(
                        text = "Farm Details",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.W400,
                        fontSize = 8.sp,
                        lineHeight = 12.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                // Show the text and title for other indices
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(if (isCurrentPage) Color.LightGray else indicatorColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = (pageIndex + 1).toString(),
                            color = if (isCurrentPage) Color.White else Color.White,
                            fontWeight = FontWeight.W700,
                            fontSize = 8.sp,
                            lineHeight = 12.sp,
                            textAlign = TextAlign.Center

                        )
                    }

                    val title = when (pageIndex) {
                        1 -> "Product Details"
                        else -> "Review"
                    }

                    Text(
                        text = title,
                        color = TextHintColor,
                        fontWeight = FontWeight.W400,
                        fontSize = 8.sp,
                        lineHeight = 12.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}




