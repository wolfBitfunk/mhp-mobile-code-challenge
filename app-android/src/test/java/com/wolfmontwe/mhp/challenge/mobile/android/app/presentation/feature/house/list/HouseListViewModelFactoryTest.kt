/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.UseCase
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.GetHousesPaginatedMock
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HouseListViewModelFactoryTest {

    private val getHousesPaginated: UseCase.GetHousesPaginated = GetHousesPaginatedMock()

    private val testSubject = HouseListViewModelFactory(
        useCaseGetHousesPaginated = getHousesPaginated
    )

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType HouseListContract.ViewModelFactory::class
    }

    @Test
    fun `SHOULD create viewModel`() = runTest {
        // GIVEN
        Dispatchers.setMain(StandardTestDispatcher())

        // WHEN
        val result = testSubject.create(HouseListViewModel::class.java)

        // THEN
        result isOfType HouseListViewModel::class
    }
}
