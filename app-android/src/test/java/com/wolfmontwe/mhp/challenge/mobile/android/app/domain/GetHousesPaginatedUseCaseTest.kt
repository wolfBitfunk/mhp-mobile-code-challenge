/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.UseCase
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Failure
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Success
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetHousesPaginatedUseCaseTest {

    private val repositoryMock = HousesRepositoryMock()
    private val paginationServiceMock = PaginationServiceMock(4, 12)

    private val testSubject: GetHousesPaginatedUseCase = GetHousesPaginatedUseCase(
        repository = repositoryMock,
        pagination = paginationServiceMock,
    )

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType UseCase.GetHousesPaginated::class
    }

    @Test
    fun `SHOULD use pagination service and repository`() = runTest {
        // GIVEN
        repositoryMock.answerGetHouses = { Success(listOf()) }

        // WHEN
        val result = testSubject.getHouses()

        // THEN
        result isOfType Success::class

        paginationServiceMock.recordedNextPage mustEqual true
        paginationServiceMock.recordedPageSize mustEqual true

        repositoryMock.recordedPage mustEqual 4
        repositoryMock.recordedPageSize mustEqual 12
    }

    @Test
    fun `SHOULD reset pagination service WHEN repository returns failure`() = runTest {
        // GIVEN
        repositoryMock.answerGetHouses = { Failure(IllegalArgumentException("Some error")) }

        // WHEN
        val result = testSubject.getHouses()

        // THEN
        result isOfType Failure::class

        paginationServiceMock.recordedReset mustEqual true
    }

    @Test
    fun `SHOULD reset pagination`() = runTest {
        // GIVEN

        // WHEN
        testSubject.reset()

        // THEN
        paginationServiceMock.recordedReset mustEqual true
    }
}
