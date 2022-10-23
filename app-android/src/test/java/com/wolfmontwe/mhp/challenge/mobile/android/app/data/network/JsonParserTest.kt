/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.network

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.CharacterResponseTestFixture
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponseTestFixture
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Failure
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Success
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import org.json.JSONException
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.Test

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
internal class JsonParserTest {

    private val testSubject: JsonParser = JsonParser()

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType Network.JsonParser::class
    }

    @Test
    fun `SHOULD return failure FOR empty json`() {
        // GIVEN
        val json = ""

        // WHEN
        val result = testSubject.parseHouse(json)

        // THEN
        result isOfType Failure::class
        result as Failure
        result.exception isOfType JSONException::class
    }

    @Test
    fun `SHOULD return failure FOR malformed json`() {
        // GIVEN
        val json = "[]"

        // WHEN
        val result = testSubject.parseHouse(json)

        // THEN
        result isOfType Failure::class
        result as Failure
        result.exception isOfType JSONException::class
    }

    @Test
    fun `SHOULD parse house json completely`() {
        // GIVEN
        val json = HouseResponseTestFixture.JSON_COMPLETE_TEST_EXAMPLE

        // WHEN
        val result = testSubject.parseHouse(json)

        // THEN
        result isOfType Success::class
        result as Success
        result.data mustEqual HouseResponseTestFixture.COMPLETE_TEST_EXAMPLE
    }

    @Test
    fun `SHOULD parse house json`() {
        // GIVEN
        val json = HouseResponseTestFixture.JSON_EXAMPLE

        // WHEN
        val result = testSubject.parseHouse(json)

        // THEN
        result isOfType Success::class
        result as Success
        result.data mustEqual HouseResponseTestFixture.EXAMPLE
    }

    @Test
    fun `SHOULD parse list of houses json completely`() {
        // GIVEN
        val json = HouseResponseTestFixture.JSON_LIST_OF_COMPLETE_TEST_EXAMPLES

        // WHEN
        val result = testSubject.parseHouses(json)

        // THEN
        result isOfType Success::class
        result as Success
        result.data.size mustEqual 2
    }

    @Test
    fun `SHOULD parse character json completely`() {
        // GIVEN
        val json = CharacterResponseTestFixture.JSON_COMPLETE_TEST_EXAMPLE

        // WHEN
        val result = testSubject.parseCharacter(json)

        // THEN
        result isOfType Success::class
        result as Success
        result.data mustEqual CharacterResponseTestFixture.COMPLETE_TEST_EXAMPLE
    }

    @Test
    fun `SHOULD parse character json`() {
        // GIVEN
        val json = CharacterResponseTestFixture.JSON_EXAMPLE

        // WHEN
        val result = testSubject.parseCharacter(json)

        // THEN
        result isOfType Success::class
        result as Success
        result.data mustEqual CharacterResponseTestFixture.EXAMPLE
    }

    @Test
    fun `SHOULD parse list of characters json completely`() {
        // GIVEN
        val json = CharacterResponseTestFixture.JSON_LIST_OF_COMPLETE_TEST_EXAMPLES

        // WHEN
        val result = testSubject.parseCharacters(json)

        // THEN
        result isOfType Success::class
        result as Success
        result.data.size mustEqual 2
    }
}
