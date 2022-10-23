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
    private val httpClient: Network.HttpClient,
    private val jsonParser: Network.JsonParser,
) : Network.IceAndFireApi {

    override suspend fun loadHouses(page: Int, pageSize: Int): Result<List<HouseResponse>> {
        val url = generatePagedUrl(url = URL_API_HOUSES, page = page, pageSize = pageSize)
        return load(url) { jsonParser.parseHouses(it) }
    }

    override suspend fun loadHouse(id: Int): Result<HouseResponse> {
        val url = URL("$URL_API_HOUSES/$id")
        return load(url) { jsonParser.parseHouse(it) }
    }

    override suspend fun loadCharacters(page: Int, pageSize: Int): Result<List<CharacterResponse>> {
        val url = generatePagedUrl(url = URL_API_CHARACTERS, page = page, pageSize = pageSize)
        return load(url) { jsonParser.parseCharacters(it) }
    }

    override suspend fun loadCharacter(id: Int): Result<CharacterResponse> {
        val url = URL("$URL_API_CHARACTERS/$id")
        return load(url) { jsonParser.parseCharacter(it) }
    }

    override suspend fun loadBooks(page: Int, pageSize: Int): Result<List<BookResponse>> {
        val url = generatePagedUrl(url = URL_API_BOOKS, page = page, pageSize = pageSize)
        return load(url) { jsonParser.parseBooks(it) }
    }

    override suspend fun loadBook(id: Int): Result<BookResponse> {
        val url = URL("$URL_API_BOOKS/$id")
        return load(url) { jsonParser.parseBook(it) }
    }

    private fun generatePagedUrl(url: String, page: Int, pageSize: Int): URL =
        URL("$url?page=$page&pageSize=$pageSize")

    private suspend fun <T> load(url: URL, parse: (String) -> Result<T>): Result<T> {
        return when (val result = httpClient.get(url)) {
            is Success -> parse(result.data)
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
