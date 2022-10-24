/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.UseCase
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.GetHousesPaginatedUseCaseMock
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.MainDispatcherRule
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HouseListViewModelFactoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getHousesPaginated: UseCase.GetHousesPaginated = GetHousesPaginatedUseCaseMock()

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

        // WHEN
        val result = testSubject.create(HouseListViewModel::class.java)

        // THEN
        result isOfType HouseListViewModel::class
    }
}
