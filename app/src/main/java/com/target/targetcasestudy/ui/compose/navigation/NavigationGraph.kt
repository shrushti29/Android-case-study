package com.target.targetcasestudy.ui.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.target.targetcasestudy.ui.compose.DetailsListScreen
import com.target.targetcasestudy.ui.compose.ProductDetailScreen
import com.target.targetcasestudy.ui.compose.navigation.Screen.ProductDetailNavRoute.getId
import com.target.targetcasestudy.ui.compose.viewModel.DealProductComposeViewModel

@Composable
fun AddNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.DealListNavRoute.route) {
        dealListScreen(navController)
        productDisplayScreen(navController)
    }
}

fun NavGraphBuilder.dealListScreen(navController: NavHostController) {
    composable(
        route = Screen.DealListNavRoute.route,
        arguments = Screen.DealListNavRoute.navArgument
    ) {
        val viewModel: DealProductComposeViewModel = hiltViewModel()
        val items = viewModel.getDealStateFlow().collectAsState().value
        DetailsListScreen(items) { value ->
            navController.navigate(Screen.ProductDetailNavRoute.route(value))
        }
    }
}


fun NavGraphBuilder.productDisplayScreen(navController: NavHostController) {
    composable(
        Screen.ProductDetailNavRoute.route,
        arguments = Screen.ProductDetailNavRoute.navArgument
    ) { backStack ->
        val id = backStack.arguments?.getId()
        ProductDetailScreen(id)
    }
}

