/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.network

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.BookResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.CharacterResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import java.io.IOException

class JsonParserMock(
    var isResultSuccess: Boolean = true
) : Network.JsonParser {

    var answerParseHouses: () -> List<HouseResponse> = {
        throw IllegalArgumentException("answerParseHouses not defined")
    }
    var answerParseHouse: () -> HouseResponse = {
        throw IllegalArgumentException("answerParseHouse not defined")
    }
    var answerParseCharacters: () -> List<CharacterResponse> = {
        throw IllegalArgumentException("answerParseCharacters not defined")
    }
    var answerParseCharacter: () -> CharacterResponse = {
        throw IllegalArgumentException("answerParseCharacter not defined")
    }
    var answerParseBooks: () -> List<BookResponse> = {
        throw IllegalArgumentException("answerParseBooks not defined")
    }
    var answerParseBook: () -> BookResponse = {
        throw IllegalArgumentException("answerParseBook not defined")
    }

    var recordedJson: String? = null
        private set

    override fun parseHouses(json: String): Result<List<HouseResponse>> {
        recordedJson = json
        return respond { answerParseHouses() }
    }

    override fun parseHouse(json: String): Result<HouseResponse> {
        recordedJson = json
        return respond { answerParseHouse() }
    }

    override fun parseCharacters(json: String): Result<List<CharacterResponse>> {
        recordedJson = json
        return respond { answerParseCharacters() }
    }

    override fun parseCharacter(json: String): Result<CharacterResponse> {
        recordedJson = json
        return respond { answerParseCharacter() }
    }

    override fun parseBooks(json: String): Result<List<BookResponse>> {
        recordedJson = json
        return respond { answerParseBooks() }
    }

    override fun parseBook(json: String): Result<BookResponse> {
        recordedJson = json
        return respond { answerParseBook() }
    }

    private fun <T> respond(block: () -> T): Result<T> {
        return if (isResultSuccess) {
            Result.success(block())
        } else {
            Result.failure(IOException("HttpClientMock error"))
        }
    }
}
