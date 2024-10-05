package com.target.targetcasestudy.ui.compose.navigation

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val navArgument: List<NamedNavArgument> = emptyList(),

    ) {
    object DealListNavRoute : Screen("List")

    object ProductDetailNavRoute : Screen(
        route = "productDetail/{id}",
        navArgument = listOf(navArgument("id") { type = NavType.IntType })
    ) {
        fun route(id: Int) = "productDetail/$id"
        fun Bundle.getId() = getInt("id")
    }
}