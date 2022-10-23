/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.navigation

interface NavigationContract {

    interface Destination {
        val route: String
    }

    interface NestedDestination : Destination {
        val rootDestination: RootDestination
    }

    interface Navigate {
        fun targetRoute(): String
    }

    interface NavigateWithArgument<T> {
        fun argumentKey(): String
        fun targetRoute(argument: T): String
    }
}
