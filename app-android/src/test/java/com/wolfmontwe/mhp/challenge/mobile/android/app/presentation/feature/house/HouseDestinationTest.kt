/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house

import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.navigation.NavigationContract
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.navigation.RootDestination
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.navigation.RootDestinationTest
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.assertSealedMemberSize
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import kotlin.test.Test

internal class HouseDestinationTest {

    @Test
    fun `SHOULD implement contract`() {
        assertSealedMemberSize(HouseDestination::class, HOUSE_DESTINATION_MEMBER_SIZE)

        HouseDestination.HouseList isOfType NavigationContract.NestedDestination::class
        HouseDestination.HouseDetail isOfType NavigationContract.NestedDestination::class
    }

    @Test
    fun `SHOULD use same root destination`() {
        for (destination in HouseDestination::class.sealedSubclasses) {
            destination.objectInstance?.rootDestination mustEqual RootDestination.House
        }
    }

    @Test
    fun `SHOULD return correct route`() {
        assertSealedMemberSize(RootDestination::class, RootDestinationTest.ROOT_DESTINATION_MEMBER_SIZE)

        val rootRoute = RootDestination.House.route

        HouseDestination.HouseList.route mustEqual "${rootRoute}/list"
        HouseDestination.HouseDetail.route mustEqual "${rootRoute}/detail/{houseId}"
    }

    @Test
    fun `SHOULD return correct targetRoute`() {
        assertSealedMemberSize(RootDestination::class, RootDestinationTest.ROOT_DESTINATION_MEMBER_SIZE)

        val rootRoute = RootDestination.House.route

        HouseDestination.HouseList isOfType NavigationContract.Navigate::class
        HouseDestination.HouseList.targetRoute() mustEqual "${rootRoute}/list"

        HouseDestination.HouseDetail isOfType NavigationContract.NavigateWithArgument::class
        HouseDestination.HouseDetail.targetRoute("123") mustEqual "${rootRoute}/detail/123"
    }

    @Test
    fun `SHOULD use correct argument key`() {
        HouseDestination.HouseDetail.argumentKey() mustEqual "houseId"
    }

    companion object {
        const val HOUSE_DESTINATION_MEMBER_SIZE = 2
    }
}
