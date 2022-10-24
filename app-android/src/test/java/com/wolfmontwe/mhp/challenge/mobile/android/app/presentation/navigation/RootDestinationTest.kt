/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.navigation

import com.wolfmontwe.mhp.challenge.mobile.android.app.test.assertSealedMemberSize
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import kotlin.test.Test

internal class RootDestinationTest {

    @Test
    fun `SHOULD implement contract`() {
        assertSealedMemberSize(RootDestination::class, ROOT_DESTINATION_MEMBER_SIZE)

        RootDestination.House isOfType NavigationContract.Destination::class
    }

    @Test
    fun `SHOULD return correct route`() {
        assertSealedMemberSize(RootDestination::class, ROOT_DESTINATION_MEMBER_SIZE)

        RootDestination.House.route mustEqual "house"
    }

    companion object {
        const val ROOT_DESTINATION_MEMBER_SIZE = 1
    }
}
