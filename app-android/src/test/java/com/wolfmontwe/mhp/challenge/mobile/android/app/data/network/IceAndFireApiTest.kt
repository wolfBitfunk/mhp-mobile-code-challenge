/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.network

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Failure
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import java.net.URL
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class IceAndFireApiTest {

    private val httpClientMock = HttpClientMock()
    private val jsonParserMock = JsonParserMock()

    private val testSubject = IceAndFireApi(httpClientMock, jsonParserMock)

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType Network.IceAndFireApi::class
    }

    @Test
    fun `SHOULD use correct url WHEN calling httpClient`() = runTest {
        httpClientMock.isResultSuccess = false

        testSubject.loadHouses()
        httpClientMock.recordedUrl mustEqual URL("https://anapioficeandfire.com/api/houses")

        testSubject.loadHouse(1)
        httpClientMock.recordedUrl mustEqual URL("https://anapioficeandfire.com/api/houses/1")

        testSubject.loadCharacters()
        httpClientMock.recordedUrl mustEqual URL("https://anapioficeandfire.com/api/characters")

        testSubject.loadCharacter(1)
        httpClientMock.recordedUrl mustEqual URL("https://anapioficeandfire.com/api/characters/1")

        testSubject.loadBooks()
        httpClientMock.recordedUrl mustEqual URL("https://anapioficeandfire.com/api/books")

        testSubject.loadBook(1)
        httpClientMock.recordedUrl mustEqual URL("https://anapioficeandfire.com/api/books/1")
    }

    @Test
    fun `SHOULD return failure WHEN httpClient has failure`() = runTest {
        // GIVEN
        httpClientMock.isResultSuccess = false

        // WHEN/THEN
        testSubject.loadHouses() isOfType Failure::class
        testSubject.loadHouse(1) isOfType Failure::class

        testSubject.loadCharacters() isOfType Failure::class
        testSubject.loadCharacter(1) isOfType Failure::class

        testSubject.loadBooks() isOfType Failure::class
        testSubject.loadBook(1) isOfType Failure::class
    }

    @Test
    fun `SHOULD return failure WHEN jsonParser has failure`() = runTest {
        // GIVEN
        jsonParserMock.isResultSuccess = false

        // WHEN/THEN
        testSubject.loadHouses() isOfType Failure::class
        testSubject.loadHouse(1) isOfType Failure::class

        testSubject.loadCharacters() isOfType Failure::class
        testSubject.loadCharacter(1) isOfType Failure::class

        testSubject.loadBooks() isOfType Failure::class
        testSubject.loadBook(1) isOfType Failure::class
    }
}
