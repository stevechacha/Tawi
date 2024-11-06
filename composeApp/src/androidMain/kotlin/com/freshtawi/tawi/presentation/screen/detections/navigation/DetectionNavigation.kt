package com.freshtawi.tawi.presentation.screen.detections.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.DETECTIONS_SCREEN_ROUTE
import com.freshtawi.tawi.presentation.screen.detections.object_detection.DetectionMainScreen
import com.freshtawi.tawi.presentation.screen.detections.object_detection.detection_options.DetectionOptionsScreen


const val DETECTION_OPTION_SCREEN_ROUTE = "Options"


@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.detectionsNavGraph(navController: NavController) {
    composable(route = DETECTIONS_SCREEN_ROUTE) {
        DetectionMainScreen(
            onOptionsButtonClick = {
                navController.navigate(DETECTION_OPTION_SCREEN_ROUTE)
            }
        )


    }
    composable(DETECTION_OPTION_SCREEN_ROUTE) {
        DetectionOptionsScreen(
            navigateBack = {
                navController.popBackStack()
            }
        )
    }
}

sealed class DetectionNavigationScreens(val route: String) {
    object DetectionOptions : DetectionNavigationScreens("option")
}
