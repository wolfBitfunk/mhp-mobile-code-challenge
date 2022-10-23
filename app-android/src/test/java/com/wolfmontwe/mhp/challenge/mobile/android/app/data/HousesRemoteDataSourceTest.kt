/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.DataSource
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.Repository
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType

import kotlin.test.Test

class HousesRemoteDataSourceTest {

    private val testSubject = HousesRemoteDataSource()

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType DataSource.Houses.Remote::class
    }
}
