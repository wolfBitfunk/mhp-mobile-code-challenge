/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.DataSource
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.IceAndFireApiMock
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponseTestFixture
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Failure
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Success
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import java.io.IOException

import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HousesRemoteDataSourceTest {

    private val apiMock = IceAndFireApiMock()

    private val testSubject = HousesRemoteDataSource(apiMock)

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType DataSource.Houses.Remote::class
    }

    @Test
    fun `SHOULD use api and provide page and pageSize`() = runTest {
        // GIVEN
        val page = 2
        val pageSize = 8
        val answer: Result<List<HouseResponse>> = Result.success(emptyList())
        apiMock.answerLoadHouses = { answer }

        // WHEN
        val result = testSubject.getHouses(page = page, pageSize = pageSize)

        // THEN
        result isOfType Success::class
        apiMock.recordedHousesPage mustEqual page
        apiMock.recordedHousesPageSize mustEqual pageSize
    }

    @Test
    fun `SHOULD return failure WHEN api fails`() = runTest {
        // GIVEN
        val page = 3
        val pageSize = 7
        val answer: Result<List<HouseResponse>> = Result.failure(IOException("Api failed"))
        apiMock.answerLoadHouses = { answer }

        // WHEN
        val result = testSubject.getHouses(page = page, pageSize = pageSize)

        // THEN
        result isOfType Failure::class
        result as Failure
        result mustEqual answer
    }

    @Test
    fun `SHOULD return success WHEN api success`() = runTest {
        // GIVEN
        val page = 3
        val pageSize = 7
        val answer: Result<List<HouseResponse>> = Result.success(listOf(HouseResponseTestFixture.EXAMPLE))
        apiMock.answerLoadHouses = { answer }

        // WHEN
        val result = testSubject.getHouses(page = page, pageSize = pageSize)

        // THEN
        result isOfType Success::class
        result as Success
        result mustEqual answer
    }
}
