/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.UseCase
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Failure
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Success
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.HouseTestFixture
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import java.io.IOException

import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetHouseByIdUseCaseTest {

    private val repositoryMock = HousesRepositoryMock()

    private val testSubject = GetHouseByIdUseCase(repositoryMock)

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType UseCase.GetHouseById::class
    }

    @Test
    fun `SHOULD use repository`() = runTest {
        // GIVEN
        val identifier = Identifier("57")
        repositoryMock.answerGetHouse = { Success(HouseTestFixture.EXAMPLE) }

        // WHEN
        val result = testSubject.getHouse(identifier)

        // THEN
        result isOfType Success::class

        repositoryMock.recordedIdentifier mustEqual identifier
    }

    @Test
    fun `SHOULD return failure WHEN repository fails`() = runTest {
        // GIVEN
        val identifier = Identifier("44")
        val answerGetHouse = Failure(IOException("Repository failed"))
        repositoryMock.answerGetHouse = { answerGetHouse }

        // WHEN
        val result = testSubject.getHouse(identifier)

        // THEN
        result isOfType Failure::class
        result as Failure
        result mustEqual answerGetHouse

    }

    @Test
    fun `SHOULD return success`() = runTest {
        // GIVEN
        val identifier = Identifier("44")
        val answerGetHouse = Success(HouseTestFixture.EXAMPLE)
        repositoryMock.answerGetHouse = { answerGetHouse }

        // WHEN
        val result = testSubject.getHouse(identifier)

        // THEN
        result isOfType Success::class
        result as Success
        result mustEqual answerGetHouse

    }
}
