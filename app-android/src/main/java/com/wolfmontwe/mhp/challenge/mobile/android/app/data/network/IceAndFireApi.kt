/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.network

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.BookResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.CharacterResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Failure
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Success
import java.net.URL

class IceAndFireApi(
    private val httpClient: Network.HttpClient
) : Network.IceAndFireApi {
    override suspend fun loadHouses(): Result<List<HouseResponse>> {
        val url = URL(URL_API_HOUSES)
        return when (val result = httpClient.get(url)) {
            is Success -> TODO("Not yet implemented")
            is Failure -> result
        }
    }

    override suspend fun loadHouse(id: Int): Result<HouseResponse> {
        val url = URL("$URL_API_HOUSES/$id")
        return when (val result = httpClient.get(url)) {
            is Success -> TODO("Not yet implemented")
            is Failure -> result
        }
    }

    override suspend fun loadCharacters(): Result<List<CharacterResponse>> {
        val url = URL(URL_API_CHARACTERS)
        return when (val result = httpClient.get(url)) {
            is Success -> TODO("Not yet implemented")
            is Failure -> result
        }
    }

    override suspend fun loadCharacter(id: Int): Result<CharacterResponse> {
        val url = URL("$URL_API_CHARACTERS/$id")
        return when (val result = httpClient.get(url)) {
            is Success -> TODO("Not yet implemented")
            is Failure -> result
        }
    }

    override suspend fun loadBooks(): Result<List<BookResponse>> {
        val url = URL(URL_API_BOOKS)
        return when (val result = httpClient.get(url)) {
            is Success -> TODO("Not yet implemented")
            is Failure -> result
        }
    }

    override suspend fun loadBook(id: Int): Result<BookResponse> {
        val url = URL("$URL_API_BOOKS/$id")
        return when (val result = httpClient.get(url)) {
            is Success -> TODO("Not yet implemented")
            is Failure -> result
        }
    }

    companion object {
        private const val BASE_URL = "https://anapioficeandfire.com/api"

        const val URL_API_BOOKS = "$BASE_URL/books"
        const val URL_API_CHARACTERS = "$BASE_URL/characters"
        const val URL_API_HOUSES = "$BASE_URL/houses"
    }
}
