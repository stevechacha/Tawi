package com.freshtawi.tawi.presentation.screen.browse

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.freshtawi.tawi.presentation.screen.browse.browse.BrowseScreen
import com.freshtawi.tawi.presentation.screen.browse.productCart.ProductCartScreen
import com.freshtawi.tawi.presentation.screen.browse.productDetail.ProductDetailScreen
import com.freshtawi.tawi.presentation.common.composable.util.DestinationGraph.BROWS_SCREEN_ROUTE

const val PRODUCT_ID = "id"
const val PRODUCT_DETAIL_ROUTE = "product_detail/{$PRODUCT_ID}"
const val PRODUCT_CART_ROUTE = "product_cart/{$PRODUCT_ID}"
const val BROWS_SCREEN_ROUTE = "browse"
const val ADD_TO_CART_ROUTE = "add_to_cart"
const val CART_SCREEN_ROUTE = "cart/{$PRODUCT_ID}"
const val EDIT_CART_ROUTE = "edit_cart/{$PRODUCT_ID}"


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.browseNavGraph(navController: NavHostController) {
    composable(route = BROWS_SCREEN_ROUTE) {
        BrowseScreen(
            navigateToProductDetail = {
                navController.navigate(
                    PRODUCT_DETAIL_ROUTE.replace("{$PRODUCT_ID}", it.toString())
                )
            }
        )
    }

    composable(
        route = PRODUCT_DETAIL_ROUTE,
        arguments = listOf(navArgument(PRODUCT_ID) { type = NavType.IntType })
    ) { navBackStackEntry ->
        val navArgument = navBackStackEntry.arguments
        val id = navArgument?.getInt(PRODUCT_ID) ?: 0
        ProductDetailScreen(
            id = id,
            navigateBack = { navController.popBackStack() },
            navigateToCart = {
                navController.navigate(
                    PRODUCT_CART_ROUTE.replace("{$PRODUCT_ID}", it.toString())
                )
            }
        )
    }

    composable(
        route = CART_SCREEN_ROUTE,
        arguments = listOf(navArgument(PRODUCT_ID) { type = NavType.IntType })
    ) { navBackStackEntry ->
        val navArgument = navBackStackEntry.arguments
        val id = navArgument?.getInt(PRODUCT_ID) ?: 0
        ProductCartScreen(
            id = id,
            navigateBack = { navController.popBackStack() }
        )
    }
    composable(
        PRODUCT_CART_ROUTE,
        arguments = listOf(navArgument(PRODUCT_ID) { type = NavType.IntType })
    ) { navBackStackEntry ->
        val navArgument = navBackStackEntry.arguments
        val id = navArgument?.getInt(PRODUCT_ID) ?: 0
        ProductCartScreen(
            id = id,
            navigateBack = { navController.popBackStack() }
        )
    }
}


