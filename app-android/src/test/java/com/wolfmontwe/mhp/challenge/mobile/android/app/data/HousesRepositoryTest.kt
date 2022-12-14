/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.Repository
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Success
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.HouseTestFixture
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HousesRepositoryTest {

    private val remoteDataSource = HousesRemoteDataSourceMock()

    private val testSubject = HousesRepository(remoteDataSource)

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType Repository.Houses::class
    }

    @Test
    fun `SHOULD use remote data source FOR getHouses`() = runTest {
        // GIVEN
        val page = 2
        val pageSize = 3
        val remoteAnswer = Result.success(listOf(HouseTestFixture.EXAMPLE))
        remoteDataSource.answerGetHouses = { remoteAnswer }

        // WHEN
        val result = testSubject.getHouses(page = page, pageSize = pageSize)

        // THEN
        result isOfType Success::class
        remoteDataSource.recordedHousesPage mustEqual page
        remoteDataSource.recordedHousesPageSize mustEqual pageSize
    }

    @Test
    fun `SHOULD use remote data source FOR getHouse`() = runTest {
        // GIVEN
        val identifier = Identifier("2")
        val remoteAnswer = Result.success(HouseTestFixture.EXAMPLE)
        remoteDataSource.answerGetHouse = { remoteAnswer }

        // WHEN
        val result = testSubject.getHouse(identifier)

        // THEN
        result isOfType Success::class
        remoteDataSource.recordedIdentifier mustEqual identifier
    }

    @Test
    fun `SHOULD return success FOR getHouses`() = runTest {
        // GIVEN
        val page = 2
        val pageSize = 3
        val remoteAnswer = Result.success(listOf(HouseTestFixture.EXAMPLE, HouseTestFixture.EXAMPLE))
        remoteDataSource.answerGetHouses = { remoteAnswer }

        // WHEN
        val result = testSubject.getHouses(page = page, pageSize = pageSize)

        // THEN
        result isOfType Success::class
        result as Success
        result mustEqual remoteAnswer
    }

    @Test
    fun `SHOULD return success FOR getHouse`() = runTest {
        // GIVEN
        val identifier = Identifier("5")
        val remoteAnswer = Result.success(HouseTestFixture.EXAMPLE)
        remoteDataSource.answerGetHouse = { remoteAnswer }

        // WHEN
        val result = testSubject.getHouse(identifier)

        // THEN
        result isOfType Success::class
        result as Success
        result mustEqual remoteAnswer
    }
}
