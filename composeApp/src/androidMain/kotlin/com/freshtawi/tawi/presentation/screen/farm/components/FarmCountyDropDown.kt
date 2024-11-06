package com.freshtawi.tawi.presentation.screen.farm.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.freshtawi.tawi.presentation.common.composable.AppOutlinedTextField
import com.freshtawi.tawi.presentation.screen.farm.state.FarmState


fun getCounties (): List<String> {
    return listOf(
        "Alameda",
        "Alpine",
        "Amador",
        "Butte",
        "Calaveras",
        "Colusa",
        "Contra Costa",
        "Del Norte",
        "El Dorado",
        "Fresno",
        "Glenn",
        "Humboldt",
        "Imperial",
        "Inyo",
        "Kern",
        "Kings",
        "Lake",
        "Lassen",
        "Los Angeles",
        "Madera",
        "Marin",
        "Mariposa",
        "Mendocino",
        "Merced",
        "Modoc",
        "Mono",
        "Monterey",
        "Napa",
        "Nevada",
        "Orange",
        "Placer",
        "Plumas",
        "Riverside",
        "Sacramento",
        "San Benito",
        "San Bernardino",
        "San Diego",
        "San Francisco",
        "San Joaquin",
        "San Luis Obispo",
        "San Mateo",
        "Santa Barbara",
        "Santa Clara",
        "Santa Cruz",
        "Shasta",
        "Sierra",
        "Siskiyou",
        "Solano",
        "Sonoma",
        "Stanislaus",
        "Sutter",
        "Tehama",
        "Trinity",
        "Tulare",
        "Tuolumne",
        "Ventura",
        "Yolo",
        "Yuba"
    )
}

@Composable
fun FarmCountyDropDown(
    farmState: FarmState,
    onCountyChanged: (String) -> Unit
) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    if (interactionSource.collectIsPressedAsState().value)
        expanded = !expanded
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    val counties = getCounties()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {

        AppOutlinedTextField(
            value = farmState.county ,
            onValueChange = { onCountyChanged(it) },
            hint = "County",
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "Expand",
                    modifier = Modifier.clickable { expanded = true }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expanded = !expanded
                }
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            interactionSource = interactionSource,
            showLabel = false
        )

        DropdownMenu(
            expanded = expanded  ,
            onDismissRequest = { expanded = false},
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
        ) {
            counties.forEach { county ->
                DropdownMenuItem(
                    text = { Text(text = county,)
                    },
                    onClick = {
                        onCountyChanged(county)
                        expanded = false
                    }
                )
            }

        }
    }

}

