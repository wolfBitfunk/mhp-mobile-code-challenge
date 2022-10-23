/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.addHouseNavigation

@Composable
fun Navigation(
    controller: NavHostController,
    startDestination: RootDestination = RootDestination.House
) {
    NavHost(
        navController = controller,
        startDestination = startDestination.route
    ) {
        addHouseNavigation(controller)
    }
}
