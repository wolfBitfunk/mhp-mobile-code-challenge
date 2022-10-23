/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.navigation

import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import kotlin.test.Test

internal class RootDestinationTest {

    @Test
    fun `SHOULD implement contract`() {
        RootDestination.House isOfType NavigationContract.Destination::class
    }

    @Test
    fun `SHOULD have house as route`() {
        RootDestination.House.route mustEqual "house"
    }
}
