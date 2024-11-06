package com.freshtawi.tawi.presentation.screen.farm.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.presentation.common.composable.AppOutlinedTextField
import com.freshtawi.tawi.presentation.common.resources.Resources


@Composable
fun FarmProvince(
    province: String,
    zipCode: String,
    onProvinceChanged: (String) -> Unit,
    onZipCodeChanged: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(.5f),
            verticalArrangement = Arrangement.Center
        ) {
            AppOutlinedTextField(
                value = province,
                onValueChange = onProvinceChanged,
                modifier = Modifier.fillMaxWidth(),
                hint = Resources.strings.regionProvince,
                showLabel = false
            )

        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            AppOutlinedTextField(
                value = zipCode,
                onValueChange = onZipCodeChanged,
                modifier = Modifier.fillMaxWidth(),
                hint = Resources.strings.zipCode,
                showLabel = false
            )

        }
    }

}