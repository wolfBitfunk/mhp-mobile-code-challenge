/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.network

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.BookResponseTestFixture
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.CharacterResponseTestFixture
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponseTestFixture
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Failure
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Success
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
    fun `SHOULD use correct json WHEN calling jsonParser`() = runTest {
        setupDefaultJsonParserAnswers()

        httpClientMock.answerGet = { "loadHouses" }
        testSubject.loadHouses()
        jsonParserMock.recordedJson mustEqual "loadHouses"

        httpClientMock.answerGet = { "loadHouse" }
        testSubject.loadHouse(1)
        jsonParserMock.recordedJson mustEqual "loadHouse"

        httpClientMock.answerGet = { "loadCharacters" }
        testSubject.loadCharacters()
        jsonParserMock.recordedJson mustEqual "loadCharacters"

        httpClientMock.answerGet = { "loadCharacter" }
        testSubject.loadCharacter(1)
        jsonParserMock.recordedJson mustEqual "loadCharacter"

        httpClientMock.answerGet = { "loadBooks" }
        testSubject.loadBooks()
        jsonParserMock.recordedJson mustEqual "loadBooks"

        httpClientMock.answerGet = { "loadBook" }
        testSubject.loadBook(1)
        jsonParserMock.recordedJson mustEqual "loadBook"
    }

    @Test
    fun `SHOULD return failure WHEN jsonParser has failure`() = runTest {
        // GIVEN
        httpClientMock.answerGet = { "success" }
        jsonParserMock.isResultSuccess = false

        // WHEN/THEN
        testSubject.loadHouses() isOfType Failure::class
        testSubject.loadHouse(1) isOfType Failure::class

        testSubject.loadCharacters() isOfType Failure::class
        testSubject.loadCharacter(1) isOfType Failure::class

        testSubject.loadBooks() isOfType Failure::class
        testSubject.loadBook(1) isOfType Failure::class
    }

    @Test
    fun `SHOULD return success with all houses`() = runTest {
        // GIVEN
        httpClientMock.answerGet = { "success" }
        jsonParserMock.answerParseHouses = { listOf(HouseResponseTestFixture.EXAMPLE) }

        // WHEN
        val result = testSubject.loadHouses()

        // THEN
        result isOfType Success::class
        result as Success
        result.data mustEqual listOf(HouseResponseTestFixture.EXAMPLE)
    }

    @Test
    fun `SHOULD return success with house`() = runTest {
        // GIVEN
        httpClientMock.answerGet = { "success" }
        jsonParserMock.answerParseHouse = { HouseResponseTestFixture.EXAMPLE }

        // WHEN
        val result = testSubject.loadHouse(1)

        // THEN
        result isOfType Success::class
        result as Success
        result.data mustEqual HouseResponseTestFixture.EXAMPLE
    }

    @Test
    fun `SHOULD return success with all characters`() = runTest {
        // GIVEN
        httpClientMock.answerGet = { "success" }
        jsonParserMock.answerParseCharacters = { listOf(CharacterResponseTestFixture.EXAMPLE) }

        // WHEN
        val result = testSubject.loadCharacters()

        // THEN
        result isOfType Success::class
        result as Success
        result.data mustEqual listOf(CharacterResponseTestFixture.EXAMPLE)
    }

    @Test
    fun `SHOULD return success with character`() = runTest {
        // GIVEN
        httpClientMock.answerGet = { "success" }
        jsonParserMock.answerParseCharacter = { CharacterResponseTestFixture.EXAMPLE }

        // WHEN
        val result = testSubject.loadCharacter(1)

        // THEN
        result isOfType Success::class
        result as Success
        result.data mustEqual CharacterResponseTestFixture.EXAMPLE
    }

    private fun setupDefaultJsonParserAnswers() {
        jsonParserMock.answerParseHouses = { listOf(HouseResponseTestFixture.EXAMPLE) }
        jsonParserMock.answerParseHouse = { HouseResponseTestFixture.EXAMPLE }
        jsonParserMock.answerParseCharacters = { listOf(CharacterResponseTestFixture.EXAMPLE) }
        jsonParserMock.answerParseCharacter = { CharacterResponseTestFixture.EXAMPLE }
        jsonParserMock.answerParseCharacter = { CharacterResponseTestFixture.EXAMPLE }
        jsonParserMock.answerParseBooks = { listOf(BookResponseTestFixture.EXAMPLE) }
        jsonParserMock.answerParseBook = { BookResponseTestFixture.EXAMPLE }
    }
}
