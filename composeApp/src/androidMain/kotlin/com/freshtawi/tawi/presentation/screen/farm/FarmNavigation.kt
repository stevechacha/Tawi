package com.freshtawi.tawi.presentation.screen.farm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.FARM_SCREEN_ROUTE
import com.freshtawi.tawi.presentation.screen.farm.addPesticide.AddPesticideScreen
import com.freshtawi.tawi.presentation.screen.farm.addProduct.AddProductScreen
import com.freshtawi.tawi.presentation.screen.farm.addProduct.AddProductsViewModel
import com.freshtawi.tawi.presentation.screen.farm.addWateringSystem.AddWateringSystemScreen
import com.freshtawi.tawi.presentation.screen.farm.creatingNewFarm.NewFarmDetailsScreen
import com.freshtawi.tawi.presentation.screen.farm.creatingNewFarm.NewFarmNameDetailsScreen
import com.freshtawi.tawi.presentation.screen.farm.creatingNewFarm.NewFarmProductDetailScreen
import com.freshtawi.tawi.presentation.screen.farm.creatingNewFarm.NewFarmReviewScreen
import com.freshtawi.tawi.presentation.screen.farm.farmLanding.FarmLandingScreen


fun NavGraphBuilder.farmNavGraph(navController: NavController) {
    composable(route = FARM_SCREEN_ROUTE) {
        FarmLandingScreen(navigator = navController)
    }
    composable(route = FarmNavigationScreen.NewFarmDetail.route) {
        NewFarmDetailsScreen(
            navigateBack = { navController.popBackStack() },
            navigateToProductDetail = {
                navController.navigate(FarmNavigationScreen.NewFarmProductDetail.route)
            }
        )
    }
    composable(route = FarmNavigationScreen.NewFarmNameDetail.route) {
        NewFarmNameDetailsScreen(navController = navController)
    }

    composable(route = FarmNavigationScreen.NewFarmProductDetail.route) {
        NewFarmProductDetailScreen(navController = navController)
    }
    composable(route = FarmNavigationScreen.NewFarmReview.route) {
        NewFarmReviewScreen(navController = navController)
    }
    composable(route = FarmNavigationScreen.AddProduct.route) {
        AddProductScreen(
            addProductsViewModel = AddProductsViewModel(),
            navController = navController
        )
    }

    composable(route = FarmNavigationScreen.AddPesticide.route) {
        AddPesticideScreen(navController = navController)
    }
    composable(route = FarmNavigationScreen.AddWateringSystem.route) {
        AddWateringSystemScreen(navController = navController)
    }
    composable(route = FarmNavigationScreen.EditProductScreen.route) {
        EditProductScreen()
    }
    composable(route = FarmNavigationScreen.GeneralFarmOverViewScreen.route) {
        GeneralFarmOverViewScreen()
    }
}

sealed class FarmNavigationScreen(val route: String) {
    data object FarmOverViewScreen : FarmNavigationScreen("farm_overview")
    data object NewFarmDetail : FarmNavigationScreen("new_farm_detail")
    data object NewFarmNameDetail : FarmNavigationScreen("new_name_farm_detail")
    data object NewFarmProductDetail : FarmNavigationScreen("new_farm_product_detail")
    data object NewFarmReview : FarmNavigationScreen("new_farm_review")
    data object AddProduct : FarmNavigationScreen("add_product")
    data object AddPesticide : FarmNavigationScreen("add_pesticide")
    data object AddWateringSystem : FarmNavigationScreen("add_watering_system ")
    data object EditProductScreen : FarmNavigationScreen("edit_product_screen")
    data object GeneralFarmOverViewScreen : FarmNavigationScreen("general_farm_overview_screen")
}

@Composable
fun GeneralFarmOverViewScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Farm Screen")
    }

}

@Composable
fun EditProductScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Farm Screen")
    }

}
