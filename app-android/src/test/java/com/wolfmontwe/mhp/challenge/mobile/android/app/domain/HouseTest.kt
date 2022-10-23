/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain

import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType

import kotlin.test.Test

class HouseTest {

    @Test
    fun `SHOULD implement contract`() {
        House("123") isOfType DomainContract.Entity::class
    }
}
