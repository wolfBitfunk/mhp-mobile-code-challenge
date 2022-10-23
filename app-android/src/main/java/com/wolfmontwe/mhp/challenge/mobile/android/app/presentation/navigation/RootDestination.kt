/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.navigation

import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.navigation.NavigationContract.Destination

sealed interface RootDestination : Destination {
    object House : RootDestination {
        override val route: String = "house"
    }
}
