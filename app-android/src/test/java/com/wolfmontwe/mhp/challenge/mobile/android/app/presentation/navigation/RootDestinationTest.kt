/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.navigation

import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import kotlin.test.Test
import kotlin.test.assertEquals

internal class RootDestinationTest {

    @Test
    fun `SHOULD implement contract`() {
        validateSealedMemberSize()

        RootDestination.House isOfType NavigationContract.Destination::class
    }

    @Test
    fun `SHOULD return correct route`() {
        validateSealedMemberSize()

        RootDestination.House.route mustEqual "house"
    }

    private fun validateSealedMemberSize() {
        val subclasses = RootDestination::class.sealedSubclasses
        assertEquals(
            expected = ROOT_DESTINATION_MEMBER_SIZE,
            actual = subclasses.size,
            message = "Sealed member size changed and test needs to be adjusted accordingly"
        )
    }

    companion object {
        const val ROOT_DESTINATION_MEMBER_SIZE = 1
    }
}
