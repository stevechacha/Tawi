package com.freshtawi.tawi.presentation.screen.auth.registration.tabs.farmerDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freshtawi.tawi.presentation.screen.auth.registration.RegistrationViewModel
import com.freshtawi.tawi.presentation.screen.auth.registration.tabs.farmerDetail.component.ProductNameSuggestion
import com.freshtawi.tawi.presentation.screen.auth.registration.tabs.farmerDetail.component.suggestedProductNameLists
import com.freshtawi.tawi.presentation.common.composable.AppButton
import com.freshtawi.tawi.presentation.common.composable.designsystem.component.HeadFirstCard
import com.freshtawi.tawi.presentation.common.composable.HorizontalIndicator
import com.freshtawi.tawi.presentation.common.composable.VunaTecDropDown
import com.freshtawi.tawi.presentation.common.composable.getCounties
import com.freshtawi.tawi.presentation.common.resources.Resources
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.ColorBorder
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.TextHintColor
import com.freshtawi.tawi.domain.model.Account
import com.freshtawi.tawi.presentation.common.composable.designsystem.theme.Poppins
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun FarmerDetailsScreen(
    onContinue: () -> Unit = {},
    farmerDetailViewModel: RegistrationViewModel = koinViewModel(),
) {
    val farmerState by farmerDetailViewModel.state.collectAsState()

    HeadFirstCard(
        textHeader = Resources.strings.tellUsAboutYou,
        textSubHeader = Resources.strings.farmerDetails,
        bottomBar = {
            AppButton(
                text = Resources.strings.continueHint,
                onClick = onContinue,
                enable = true
            )
        },
        pageIndicators = { HorizontalIndicator(3, currentPage = 1) }
    ) {

        FarmerProductName(
            farmerProductName = farmerState.farmerProductName,
            onProductNameChanged = farmerDetailViewModel::setFarmerProductName
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProductNameSuggestion(
            onClickProduct = farmerDetailViewModel::setFarmerProductName,
            suggestedProductNameList = suggestedProductNameLists
        )
        Spacer(modifier = Modifier.height(16.dp))
        VunaTecDropDown(
            value = farmerState.farmerShipProductModel,
            onValueChanged = farmerDetailViewModel::setFarmerShipProductModel,
            data = getCounties(),
            hint = Resources.strings.buyerPickUpOrOrganized,
            label = Resources.strings.farmerShipMeans,
            showLabel = true
        )

    }
}

@Composable
fun Texts(
    modifier: Modifier = Modifier,
    account: Account
) {
    Column(
        modifier = modifier.fillMaxWidth().height(140.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = account.county )
        Text(text = account.zipCode )
        Text(text = account.province )
        Text(text = account.farmerShipMethod )
        Text(text = account.farmerProductName )
    }

}



@Composable
private fun FarmerProductName(
    onProductNameChanged: (String) -> Unit,
    farmerProductName: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = Resources.strings.farmerSellCrops,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 20.sp,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = Resources.strings.learnMore,
                fontSize = 12.sp,
                fontWeight = FontWeight.W700,
                lineHeight = 18.sp,
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.End
            )
        }
        Spacer(modifier = Modifier.height(5.dp))


        OutlinedTextField(
            value = farmerProductName,
            onValueChange = onProductNameChanged,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedBorderColor = ColorBorder,
                unfocusedBorderColor = ColorBorder,
            ),
            placeholder = {
                Text(
                    text = Resources.strings.productName,
                    color = TextHintColor,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.W400,
                        lineHeight = 18.sp,
                    )
                )
            },
            shape = RoundedCornerShape(8.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        )

    }

}



