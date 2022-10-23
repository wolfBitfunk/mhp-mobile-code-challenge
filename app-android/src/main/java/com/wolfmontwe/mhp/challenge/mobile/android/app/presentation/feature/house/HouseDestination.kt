/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house

import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.navigation.NavigationContract.Navigate
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.navigation.NavigationContract.NavigateWithArgument
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.navigation.NavigationContract.NestedDestination
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.navigation.RootDestination

sealed class HouseDestination : NestedDestination {
    override val rootDestination: RootDestination = RootDestination.House

    object HouseList : HouseDestination(), Navigate {
        override val route: String = "${rootDestination.route}/list"
        override fun targetRoute(): String = route
    }

    object HouseDetail : HouseDestination(), NavigateWithArgument<Int> {
        const val ARGUMENT_KEY_HOUSE_ID = "houseId"

        override val route: String = "${rootDestination.route}/detail/{$ARGUMENT_KEY_HOUSE_ID}"

        override fun argumentKey(): String = ARGUMENT_KEY_HOUSE_ID
        override fun targetRoute(argument: Int): String = "${rootDestination.route}/detail/$argument"
    }
}
