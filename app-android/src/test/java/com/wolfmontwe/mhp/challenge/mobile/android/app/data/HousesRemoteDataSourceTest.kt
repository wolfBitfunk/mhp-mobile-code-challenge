/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.DataSource
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.mapper.HousesMapperMock
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.IceAndFireApiMock
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponseTestFixture
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Failure
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Success
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.HouseTestFixture
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import java.io.IOException

import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HousesRemoteDataSourceTest {

    private val apiMock = IceAndFireApiMock()
    private val mapperMock = HousesMapperMock()

    private val testSubject = HousesRemoteDataSource(apiMock, mapperMock)

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType DataSource.Houses.Remote::class
    }

    @Test
    fun `SHOULD use api and provide page and pageSize FOR getHouses`() = runTest {
        // GIVEN
        val page = 2
        val pageSize = 8
        val apiAnswer: Result<List<HouseResponse>> = Result.success(emptyList())
        val mapperAnswer: Result<List<House>> = Result.success(emptyList())
        apiMock.answerLoadHouses = { apiAnswer }
        mapperMock.answerMapToDomainList = { mapperAnswer }

        // WHEN
        val result = testSubject.getHouses(page = page, pageSize = pageSize)

        // THEN
        result isOfType Success::class
        apiMock.recordedHousesPage mustEqual page
        apiMock.recordedHousesPageSize mustEqual pageSize
    }

    @Test
    fun `SHOULD use api and provide page and pageSize FOR getHouse`() = runTest {
        // GIVEN
        val identifier = Identifier("3")
        val apiAnswer: Result<HouseResponse> = Result.success(HouseResponseTestFixture.EXAMPLE)
        val mapperAnswer: Result<House> = Result.success(HouseTestFixture.EXAMPLE)
        apiMock.answerLoadHouse = { apiAnswer }
        mapperMock.answerMapToDomainObject = { mapperAnswer }

        // WHEN
        val result = testSubject.getHouse(identifier)

        // THEN
        result isOfType Success::class
        apiMock.recordedHouseId mustEqual identifier.value
    }

    @Test
    fun `SHOULD return failure WHEN api fails FOR getHouses`() = runTest {
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
    fun `SHOULD return failure WHEN api fails FOR getHouse`() = runTest {
        // GIVEN
        val identifier = Identifier("55")
        val answer: Result<HouseResponse> = Result.failure(IOException("Api failed"))
        apiMock.answerLoadHouse = { answer }

        // WHEN
        val result = testSubject.getHouse(identifier)

        // THEN
        result isOfType Failure::class
        result as Failure
        result mustEqual answer
    }

    @Test
    fun `SHOULD use mapper and provide response data FOR getHouses`() = runTest {
        // GIVEN
        val page = 2
        val pageSize = 8
        val apiResponse = listOf(HouseResponseTestFixture.EXAMPLE)
        val apiAnswer: Result<List<HouseResponse>> = Result.success(apiResponse)
        val mapperAnswer: Result<List<House>> = Result.success(emptyList())
        apiMock.answerLoadHouses = { apiAnswer }
        mapperMock.answerMapToDomainList = { mapperAnswer }

        // WHEN
        val result = testSubject.getHouses(page = page, pageSize = pageSize)

        // THEN
        result isOfType Success::class
        result as Success
        result mustEqual mapperAnswer
        mapperMock.recordedHouseResponseList mustEqual apiResponse
    }

    @Test
    fun `SHOULD use mapper and provide response data FOR getHouse`() = runTest {
        // GIVEN
        val identifier = Identifier("89")
        val apiResponse = HouseResponseTestFixture.EXAMPLE
        val apiAnswer: Result<HouseResponse> = Result.success(apiResponse)
        val mapperAnswer: Result<House> = Result.success(HouseTestFixture.EXAMPLE)
        apiMock.answerLoadHouse = { apiAnswer }
        mapperMock.answerMapToDomainObject = { mapperAnswer }

        // WHEN
        val result = testSubject.getHouse(identifier)

        // THEN
        result isOfType Success::class
        result as Success
        result mustEqual mapperAnswer
        mapperMock.recordedHouseResponse mustEqual apiResponse
    }

    @Test
    fun `SHOULD return failure WHEN mapper fails FOR getHouses`() = runTest {
        // GIVEN
        val page = 3
        val pageSize = 7
        val apiAnswer: Result<List<HouseResponse>> = Result.success(emptyList())
        val mapperAnswer: Result<List<House>> = Result.failure(IOException("Mapper failed"))
        apiMock.answerLoadHouses = { apiAnswer }
        mapperMock.answerMapToDomainList = { mapperAnswer }

        // WHEN
        val result = testSubject.getHouses(page = page, pageSize = pageSize)

        // THEN
        result isOfType Failure::class
        result as Failure
        result mustEqual mapperAnswer
    }

    @Test
    fun `SHOULD return failure WHEN mapper fails FOR getHouse`() = runTest {
        // GIVEN
        val identifier = Identifier("45")
        val apiAnswer: Result<HouseResponse> = Result.success(HouseResponseTestFixture.EXAMPLE)
        val mapperAnswer: Result<House> = Result.failure(IOException("Mapper failed"))
        apiMock.answerLoadHouse = { apiAnswer }
        mapperMock.answerMapToDomainObject = { mapperAnswer }

        // WHEN
        val result = testSubject.getHouse(identifier)

        // THEN
        result isOfType Failure::class
        result as Failure
        result mustEqual mapperAnswer
    }

    @Test
    fun `SHOULD return success WHEN api success FOR getHouses`() = runTest {
        // GIVEN
        val page = 3
        val pageSize = 7
        val apiAnswer: Result<List<HouseResponse>> = Result.success(listOf(HouseResponseTestFixture.EXAMPLE))
        val mapperAnswer: Result<List<House>> = Result.success(listOf(HouseTestFixture.EXAMPLE))
        apiMock.answerLoadHouses = { apiAnswer }
        mapperMock.answerMapToDomainList = { mapperAnswer }

        // WHEN
        val result = testSubject.getHouses(page = page, pageSize = pageSize)

        // THEN
        result isOfType Success::class
        result as Success
        result mustEqual mapperAnswer
    }

    @Test
    fun `SHOULD return success WHEN api success FOR getHouse`() = runTest {
        // GIVEN
        val identifier = Identifier("4")
        val apiAnswer: Result<HouseResponse> = Result.success(HouseResponseTestFixture.EXAMPLE)
        val mapperAnswer: Result<House> = Result.success(HouseTestFixture.EXAMPLE)
        apiMock.answerLoadHouse = { apiAnswer }
        mapperMock.answerMapToDomainObject = { mapperAnswer }

        // WHEN
        val result = testSubject.getHouse(identifier)

        // THEN
        result isOfType Success::class
        result as Success
        result mustEqual mapperAnswer
    }
}
