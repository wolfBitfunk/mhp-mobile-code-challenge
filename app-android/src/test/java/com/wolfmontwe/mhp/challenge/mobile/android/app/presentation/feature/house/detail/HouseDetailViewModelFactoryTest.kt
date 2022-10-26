/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.detail

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.UseCase
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.GetHouseByIdMock
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HouseDetailViewModelFactoryTest {

    private val getHouseByIdUseCase: UseCase.GetHouseById = GetHouseByIdMock()

    private val testSubject = HouseDetailViewModelFactory(
        houseId = "123",
        useCaseGetHouseById = getHouseByIdUseCase
    )

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType HouseDetailContract.ViewModelFactory::class
    }

    @Test
    fun `SHOULD create viewModel`() = runTest {
        // GIVEN
        Dispatchers.setMain(StandardTestDispatcher())

        // WHEN
        val result = testSubject.create(HouseDetailViewModel::class.java)

        // THEN
        result isOfType HouseDetailViewModel::class
    }
}
