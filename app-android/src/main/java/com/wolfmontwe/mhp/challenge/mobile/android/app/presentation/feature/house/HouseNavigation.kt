/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.detail.HouseDetailView
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list.HouseListView
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.navigation.RootDestination

fun NavGraphBuilder.addHouseNavigation(
    controller: NavController
) {
    navigation(
        startDestination = HouseDestination.HouseList.route,
        route = RootDestination.House.route
    ) {
        composable(route = HouseDestination.HouseList.route) {
            HouseListView {
                controller.navigate(HouseDestination.HouseDetail.targetRoute(it))
            }
        }
        composable(
            route = HouseDestination.HouseDetail.route,
            arguments = listOf(navArgument(HouseDestination.HouseDetail.ARGUMENT_KEY_HOUSE_ID) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val houseId = backStackEntry.arguments!!.getString(HouseDestination.HouseDetail.ARGUMENT_KEY_HOUSE_ID)
            HouseDetailView(houseId!!)
        }
    }
}
