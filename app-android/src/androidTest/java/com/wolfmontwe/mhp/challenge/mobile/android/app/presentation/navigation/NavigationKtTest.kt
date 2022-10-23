/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.navigation

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.runComposeUiTest
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.HouseDestination
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class NavigationKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun should_navigate_to_house_list_when_root_destination_house_is_default() = runComposeUiTest {
        // GIVEN
        lateinit var navController: NavHostController

        // WHEN
        setContent {
            navController = rememberNavController()
            Navigation(controller = navController)
        }

        // THEN
        val route = navController.currentBackStackEntry?.destination?.route
        route mustEqual HouseDestination.HouseList.route
    }

    @Test
    fun should_navigate_to_house_list_when_start_root_destination_is_house() = runComposeUiTest {
        // GIVEN
        lateinit var navController: NavHostController

        // WHEN
        setContent {
            navController = rememberNavController()
            Navigation(controller = navController, startDestination = RootDestination.House)
        }

        // THEN
        val route = navController.currentBackStackEntry?.destination?.route
        route mustEqual HouseDestination.HouseList.route
    }

    @Test
    fun should_navigate_to_house_list_when_navigating_to_root_destination_house() = runComposeUiTest {
        // GIVEN
        lateinit var navController: NavHostController
        val targetRoute = RootDestination.House.route

        // WHEN
        setContent {
            navController = rememberNavController()
            Navigation(controller = navController)

            navController.navigate(targetRoute)
        }

        // THEN
        val route = navController.currentBackStackEntry?.destination?.route
        route mustEqual HouseDestination.HouseList.route
    }

    @Test
    fun should_navigate_to_house_list() = runComposeUiTest {
        // GIVEN
        lateinit var navController: NavHostController
        val targetRoute = HouseDestination.HouseList.targetRoute()

        // WHEN
        setContent {
            navController = rememberNavController()
            Navigation(controller = navController)

            navController.navigate(targetRoute)
        }

        // THEN
        val route = navController.currentBackStackEntry?.destination?.route
        route mustEqual HouseDestination.HouseList.route
    }

    @Test
    fun should_navigate_to_house_detail_and_use_argument() = runComposeUiTest {
        // GIVEN
        lateinit var navController: NavHostController
        val targetArgument = 123
        val targetRoute = HouseDestination.HouseDetail.targetRoute(targetArgument)

        // WHEN
        setContent {
            navController = rememberNavController()
            Navigation(controller = navController)

            navController.navigate(targetRoute)
        }

        // THEN
        val route = navController.currentBackStackEntry?.destination?.route
        route mustEqual HouseDestination.HouseDetail.route
        val argument = navController
            .currentBackStackEntry?.arguments?.getInt(HouseDestination.HouseDetail.argumentKey())
        argument mustEqual targetArgument
    }
}
