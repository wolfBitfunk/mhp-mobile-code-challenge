/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType

import kotlin.test.Test

class HouseTest {

    private val testSubject = HouseTestFixture.EXAMPLE

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType DomainContract.Entity::class
    }
}
