package com.freshtawi.tawi.presentation.common.bottomNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Deck
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SelectAll
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.freshtawi.tawi.R
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.ALERTS_SCREEN_ROUTE
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.CALENDER_SCREEN_ROUTE
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.DETECTIONS_SCREEN_ROUTE
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.FARM_SCREEN_ROUTE
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.PAYMENTS_SCREEN_ROUTE
import com.freshtawi.tawi.presentation.common.navigation.Screens
import com.freshtawi.tawi.presentation.common.resources.Resources


data class BottomNavItem(
    val screen: String,
    val icon: ImageVector? = null,
    val title: String? = null,
    val contentDescription: String? = null,
    val alertCount: Int? = null,
)


@Composable
fun getBuyerBottomNavigation(): List<BottomNavItem> = listOf(
    BottomNavItem(
        screen =  DestinationGraph.BROWS_SCREEN_ROUTE,
        icon = Icons.Filled.SelectAll,
        title =  Resources.strings.browsMenuTitle
    ),
    BottomNavItem(
        screen =  DestinationGraph.PAYMENTS_SCREEN_ROUTE,
        icon =   Icons.Filled.Payment,
        title =   Resources.strings.paymentMenuTitle
    ),
    BottomNavItem(
        screen =  DestinationGraph.ACCOUNTS_SCREEN_ROUTE,
        icon =  Icons.Filled.Person,
        title =  Resources.strings.accountMenuTitle
    ),
)

@Composable
fun getFarmerBottomNavigation(): List<BottomNavItem> = listOf(
    BottomNavItem(
        screen =  FARM_SCREEN_ROUTE,
        icon =  Icons.Filled.Home,
        title =  Resources.strings.farmMenuTitle
    ),
    BottomNavItem(
        screen =  CALENDER_SCREEN_ROUTE,
        icon =  Icons.Filled.CalendarToday,
        title =  Resources.strings.calendarMenuTitle
    ),
    BottomNavItem(
        screen =  DETECTIONS_SCREEN_ROUTE,
        icon =  Icons.Filled.Deck,
        title =  Resources.strings.dataMenuTitle
    ),
    BottomNavItem(
        screen =  PAYMENTS_SCREEN_ROUTE,
        icon =  Icons.Filled.Payment,
        title =  Resources.strings.paymentMenuTitle
    ),
    BottomNavItem(
        screen =  ALERTS_SCREEN_ROUTE,
        icon =  Icons.Filled.Person,
        title =  Resources.strings.alertMenuTitle
    ),
)
