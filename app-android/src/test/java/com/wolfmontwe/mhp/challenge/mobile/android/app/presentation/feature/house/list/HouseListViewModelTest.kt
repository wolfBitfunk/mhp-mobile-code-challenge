/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.GetHousesPaginatedMock
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.MainDispatcherRule
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import org.junit.Rule
import kotlin.test.BeforeTest

import kotlin.test.Test

class HouseListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getHousesPaginatedMock = GetHousesPaginatedMock()

    private lateinit var testSubject: HouseListViewModel

    @BeforeTest
    fun setup() {
        testSubject = HouseListViewModel(getHousesPaginatedMock)
    }

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType HouseListContract.ViewModel::class
    }
}
