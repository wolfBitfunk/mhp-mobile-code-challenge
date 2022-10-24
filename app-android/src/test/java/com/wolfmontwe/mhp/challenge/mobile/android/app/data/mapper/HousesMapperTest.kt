/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.mapper

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Mapper
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponseTestFixture
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Failure
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Success
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.HouseTestFixture
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import kotlin.test.Test

class HousesMapperTest {

    private val idMapperMock = IdentifierMapperMock()

    private val testSubject = HousesMapper(idMapperMock)

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType Mapper.Houses::class
    }

    @Test
    fun `SHOULD use Id mapper`() {
        // GIVEN
        val houseResponse = HouseResponseTestFixture.EXAMPLE
        val mapperAnswer = Result.success(Identifier("378"))
        idMapperMock.answerMapToDomainObject = { mapperAnswer }

        // WHEN
        val result = testSubject.mapToDomain(houseResponse)

        // THEN
        result isOfType Success::class
        idMapperMock.recordedIdentifier mustEqual HouseResponseTestFixture.EXAMPLE.url
    }

    @Test
    fun `SHOULD return failure WHEN Id mapper fails`() {
        // GIVEN
        val houseResponse = HouseResponseTestFixture.EXAMPLE
        val mapperAnswer = Result.failure(IllegalArgumentException("Id mapper failed"))
        idMapperMock.answerMapToDomainObject = { mapperAnswer }

        // WHEN
        val result = testSubject.mapToDomain(houseResponse)

        // THEN
        result isOfType Failure::class
        result as Failure
        result mustEqual mapperAnswer
    }

    @Test
    fun `SHOULD map to domain object`() {
        // GIVEN
        val houseResponse = HouseResponseTestFixture.EXAMPLE
        val mapperAnswer = Result.success(HouseTestFixture.EXAMPLE.id)
        idMapperMock.answerMapToDomainObject = { mapperAnswer }

        // WHEN
        val result = testSubject.mapToDomain(houseResponse)

        // THEN
        result isOfType Success::class
        result as Success
        result.data mustEqual HouseTestFixture.EXAMPLE
    }

    @Test
    fun `SHOULD map to domain list`() {
        // GIVEN
        val houseResponses = listOf(
            HouseResponseTestFixture.EXAMPLE,
            HouseResponseTestFixture.EXAMPLE,
        )
        val mapperAnswer = Result.success(HouseTestFixture.EXAMPLE.id)
        idMapperMock.answerMapToDomainObject = { mapperAnswer }

        // WHEN
        val result = testSubject.mapToDomain(houseResponses)

        // THEN
        result isOfType Success::class
        result as Success
        result.data mustEqual listOf(
            HouseTestFixture.EXAMPLE,
            HouseTestFixture.EXAMPLE,
        )
    }

    @Test
    fun `SHOULD map empty response list to empty list`() {
        // GIVEN
        val houseResponses: List<HouseResponse> = emptyList()
        val mapperAnswer = Result.success(HouseTestFixture.EXAMPLE.id)
        idMapperMock.answerMapToDomainObject = { mapperAnswer }

        // WHEN
        val result = testSubject.mapToDomain(houseResponses)

        // THEN
        result isOfType Success::class
        result as Success
        result.data mustEqual emptyList()
        idMapperMock.recordedIdentifier mustEqual null
    }
}
