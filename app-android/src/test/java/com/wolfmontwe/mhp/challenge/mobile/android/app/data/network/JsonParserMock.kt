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

    var recordedJson: String? = null
        private set

    override fun parseHouses(json: String): Result<List<HouseResponse>> {
        recordedJson = json
        return if (isResultSuccess) {
            TODO("Not yet implemented")
        } else {
            Result.failure(IOException("HttpClientMock error"))
        }
    }

    override fun parseHouse(json: String): Result<HouseResponse> {
        recordedJson = json
        return if (isResultSuccess) {
            TODO("Not yet implemented")
        } else {
            Result.failure(IOException("HttpClientMock error"))
        }
    }

    override fun parseCharacters(json: String): Result<List<CharacterResponse>> {
        recordedJson = json
        return if (isResultSuccess) {
            TODO("Not yet implemented")
        } else {
            Result.failure(IOException("HttpClientMock error"))
        }
    }

    override fun parseCharacter(json: String): Result<CharacterResponse> {
        recordedJson = json
        return if (isResultSuccess) {
            TODO("Not yet implemented")
        } else {
            Result.failure(IOException("HttpClientMock error"))
        }
    }

    override fun parseBooks(json: String): Result<List<BookResponse>> {
        recordedJson = json
        return if (isResultSuccess) {
            TODO("Not yet implemented")
        } else {
            Result.failure(IOException("HttpClientMock error"))
        }
    }

    override fun parseBook(json: String): Result<BookResponse> {
        recordedJson = json
        return if (isResultSuccess) {
            TODO("Not yet implemented")
        } else {
            Result.failure(IOException("HttpClientMock error"))
        }
    }
}
