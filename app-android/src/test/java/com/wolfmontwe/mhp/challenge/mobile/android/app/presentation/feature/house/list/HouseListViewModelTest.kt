/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.GetHousesPaginatedUseCaseMock
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.HouseTestFixture
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.MainDispatcherRule
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.runTestWithStateFlowCollector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import org.junit.Rule
import java.io.IOException
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HouseListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var useCaseGetHousesPaginated: GetHousesPaginatedUseCaseMock

    private lateinit var collectedStateFlows: MutableList<HouseListViewState>

    private lateinit var testSubject: HouseListViewModel

    @BeforeTest
    fun setup() {
        useCaseGetHousesPaginated = GetHousesPaginatedUseCaseMock()
        useCaseGetHousesPaginated.answerGetHouses = { HouseTestFixture.RESULT_SUCCESS_EXAMPLE_LIST }

        collectedStateFlows = mutableListOf()


        testSubject = HouseListViewModel(
            useCaseGetHousesPaginated = useCaseGetHousesPaginated,
            ioDispatcher = mainDispatcherRule.testDispatcher
        )
    }

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType HouseListContract.ViewModel::class
    }

    @Test
    fun `SHOULD have initial state WHEN created`() = runTestWithStateRecording {
        // GIVEN
        val initialState = STATE_AFTER_INIT

        // WHEN
        val result = testSubject.state.value

        // THEN
        result mustEqual initialState
        collectedStateFlows.size mustEqual 1
        collectedStateFlows[0] mustEqual initialState
    }

    @Test
    fun `SHOULD set error state WHEN use case fails`() = runTestWithStateRecording {
        // GIVEN
        useCaseGetHousesPaginated.answerGetHouses = { Result.failure(IOException("useCaseGetHousesPaginated failed")) }
        val initialState = STATE_AFTER_INIT
        val loadingState = initialState.copy(isLoading = true)
        val errorState = loadingState.copy(error = "useCaseGetHousesPaginated failed")
        val endState = errorState.copy(isLoading = false)

        // WHEN
        testSubject.loadMoreItems()

        // THEN
        collectedStateFlows.size mustEqual 4
        collectedStateFlows[0] mustEqual initialState
        collectedStateFlows[1] mustEqual loadingState
        collectedStateFlows[2] mustEqual errorState
        collectedStateFlows[3] mustEqual endState
        useCaseGetHousesPaginated.recordedGetHouses mustEqual true
    }

    @Test
    fun `SHOULD set success state WHEN use case run`() = runTestWithStateRecording {
        // GIVEN
        useCaseGetHousesPaginated.answerGetHouses = { HouseTestFixture.RESULT_SUCCESS_EXAMPLE_LIST }
        val initialState = STATE_AFTER_INIT
        val loadingState = initialState.copy(isLoading = true)
        val successState = loadingState.copy(items = loadingState.items + HouseTestFixture.EXAMPLE_LIST)
        val endState = successState.copy(isLoading = false)

        // WHEN
        testSubject.loadMoreItems()

        // THEN
        collectedStateFlows.size mustEqual 4
        collectedStateFlows[0] mustEqual initialState
        collectedStateFlows[1] mustEqual loadingState
        collectedStateFlows[2] mustEqual successState
        collectedStateFlows[3] mustEqual endState
        useCaseGetHousesPaginated.recordedGetHouses mustEqual true
    }

    @Test
    fun `SHOULD set isPagingEnd WHEN empty use case result`() = runTestWithStateRecording {
        // GIVEN
        useCaseGetHousesPaginated.answerGetHouses = { Result.success(emptyList()) }
        val initialState = STATE_AFTER_INIT
        val loadingState = initialState.copy(isLoading = true)
        val pageEndState = loadingState.copy(isPagingEnd = true)
        val endState = pageEndState.copy(isLoading = false)

        // WHEN
        testSubject.loadMoreItems()

        // THEN
        collectedStateFlows.size mustEqual 4
        collectedStateFlows[0] mustEqual initialState
        collectedStateFlows[1] mustEqual loadingState
        collectedStateFlows[2] mustEqual pageEndState
        collectedStateFlows[3] mustEqual endState
        useCaseGetHousesPaginated.recordedGetHouses mustEqual true
    }

    @Test
    fun `SHOULD prevent loadMoreItems WHEN isPagingEnd`() = runTestWithStateFlowCollector(
        collectedStateFlows = collectedStateFlows,
        stateFlow = createTestSubject(
            initialViewState = HouseListViewState().copy(isPagingEnd = true)
        ).also { testSubject = it }.state,
    ) {
        // WHEN
        testSubject.loadMoreItems()

        // THEN
        collectedStateFlows.size mustEqual 1
        useCaseGetHousesPaginated.recordedGetHouses mustEqual false
    }

    @Test
    fun `SHOULD prevent loadMoreItems WHEN isLoading`() = runTestWithStateFlowCollector(
        collectedStateFlows = collectedStateFlows,
        stateFlow = createTestSubject(
            initialViewState = HouseListViewState().copy(isLoading = true)
        ).also { testSubject = it }.state,
    ) {
        // WHEN
        testSubject.loadMoreItems()

        // THEN
        collectedStateFlows.size mustEqual 1
        useCaseGetHousesPaginated.recordedGetHouses mustEqual false
    }

    @Test
    fun `SHOULD reset use case and change to initial state`() = runTestWithStateFlowCollector(
        collectedStateFlows = collectedStateFlows,
        stateFlow = createTestSubject(
            initialViewState = HouseListViewState().copy(items = HouseTestFixture.EXAMPLE_LIST)
        ).also { testSubject = it }.state,
    ) {
        // GIVEN
        val initialState = testSubject.state.value
        val resetState = initialState.copy(items = emptyList())

        // WHEN
        testSubject.reset()

        // THEN
        collectedStateFlows.size mustEqual 2
        collectedStateFlows[0] mustEqual initialState
        collectedStateFlows[1] mustEqual resetState
        useCaseGetHousesPaginated.recordedReset mustEqual true
    }

    private fun runTestWithStateRecording(executeTest: TestScope.() -> Unit) = runTestWithStateFlowCollector(
        collectedStateFlows = collectedStateFlows,
        stateFlow = testSubject.state,
        executeTest = executeTest
    )

    private fun createTestSubject(
        initialViewState: HouseListViewState = HouseListViewState(),
    ): HouseListViewModel {
        return HouseListViewModel(
            useCaseGetHousesPaginated = GetHousesPaginatedUseCaseMock().also { useCaseGetHousesPaginated = it },
            ioDispatcher = mainDispatcherRule.testDispatcher,
            initialViewState = initialViewState
        )
    }

    private companion object {
        val STATE_AFTER_INIT = HouseListViewState(items = HouseTestFixture.EXAMPLE_LIST)
    }
}
