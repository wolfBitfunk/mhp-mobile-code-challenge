/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.Repository
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType

import kotlin.test.Test

class HousesRepositoryTest {

    private val testSubject = HousesRepository()

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType Repository.Houses::class
    }
}
