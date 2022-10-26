/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.detail

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.GetHouseByIdMock
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.HouseTestFixture
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier
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
class HouseDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var collectedStateFlows: MutableList<HouseDetailViewState>

    private lateinit var useCaseGetHouseByIdMock: GetHouseByIdMock

    private lateinit var testSubject: HouseDetailViewModel

    @BeforeTest
    fun setup() {
        useCaseGetHouseByIdMock = GetHouseByIdMock()
        useCaseGetHouseByIdMock.answerGetHouse = { HouseTestFixture.RESULT_SUCCESS_EXAMPLE }

        collectedStateFlows = mutableListOf()

        testSubject = HouseDetailViewModel(
            houseId = HOUSE_ID,
            useCaseGetHouseById = useCaseGetHouseByIdMock,
            ioDispatcher = mainDispatcherRule.testDispatcher
        )
    }

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType HouseDetailContract.ViewModel::class
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
        useCaseGetHouseByIdMock.answerGetHouse = { Result.failure(IOException("useCaseGetHouseById failed")) }
        val initialState = STATE_AFTER_INIT
        val loadingState = initialState.copy(isLoading = true)
        val errorState = loadingState.copy(error = "useCaseGetHouseById failed")
        val endState = errorState.copy(isLoading = false)

        // WHEN
        testSubject.loadItem()

        // THEN
        collectedStateFlows.size mustEqual 4
        collectedStateFlows[0] mustEqual initialState
        collectedStateFlows[1] mustEqual loadingState
        collectedStateFlows[2] mustEqual errorState
        collectedStateFlows[3] mustEqual endState
        useCaseGetHouseByIdMock.recordedIdentifier mustEqual IDENTIFIER
    }

    @Test
    fun `SHOULD set success state WHEN use case run`() = runTestWithStateRecording {
        // GIVEN
        val item = HouseTestFixture.EXAMPLE.copy(name = "OtherName")
        useCaseGetHouseByIdMock.answerGetHouse = { Result.success(item) }
        val initialState = STATE_AFTER_INIT
        val loadingState = initialState.copy(isLoading = true)
        val successState = loadingState.copy(item = item)
        val endState = successState.copy(isLoading = false)

        // WHEN
        testSubject.loadItem()

        // THEN
        collectedStateFlows.size mustEqual 4
        collectedStateFlows[0] mustEqual initialState
        collectedStateFlows[1] mustEqual loadingState
        collectedStateFlows[2] mustEqual successState
        collectedStateFlows[3] mustEqual endState
        useCaseGetHouseByIdMock.recordedIdentifier mustEqual IDENTIFIER
    }

    @Test
    fun `SHOULD prevent loadMoreItems WHEN isLoading`() = runTestWithStateFlowCollector(
        collectedStateFlows = collectedStateFlows,
        stateFlow = createTestSubject(
            initialViewState = HouseDetailViewState().copy(isLoading = true)
        ).also { testSubject = it }.state,
    ) {
        // WHEN
        testSubject.loadItem()

        // THEN
        collectedStateFlows.size mustEqual 1
        useCaseGetHouseByIdMock.recordedIdentifier mustEqual null
    }

    private fun runTestWithStateRecording(executeTest: TestScope.() -> Unit) = runTestWithStateFlowCollector(
        collectedStateFlows = collectedStateFlows,
        stateFlow = testSubject.state,
        executeTest = executeTest
    )

    private fun createTestSubject(
        initialViewState: HouseDetailViewState = HouseDetailViewState(),
    ): HouseDetailViewModel {
        return HouseDetailViewModel(
            houseId = HOUSE_ID,
            useCaseGetHouseById = GetHouseByIdMock().also { useCaseGetHouseByIdMock = it },
            ioDispatcher = mainDispatcherRule.testDispatcher,
            initialViewState = initialViewState
        )
    }

    private companion object {
        const val HOUSE_ID = "589"
        val IDENTIFIER = Identifier(value = HOUSE_ID)
        val STATE_AFTER_INIT = HouseDetailViewState(
            identifier = IDENTIFIER,
            item = HouseTestFixture.EXAMPLE
        )
    }
}
