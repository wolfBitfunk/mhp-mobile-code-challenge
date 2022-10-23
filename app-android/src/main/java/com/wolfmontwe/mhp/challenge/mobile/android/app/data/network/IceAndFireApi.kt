/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.network

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.BookResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.CharacterResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result

class IceAndFireApi(
    private val httpClient: Network.HttpClient
) : Network.IceAndFireApi {
    override suspend fun loadHouses(): Result<List<HouseResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun loadHouse(id: Int): Result<HouseResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun loadCharacters(): Result<List<CharacterResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun loadCharacter(id: Int): Result<CharacterResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun loadBooks(): Result<List<BookResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun loadBook(id: Int): Result<BookResponse> {
        TODO("Not yet implemented")
    }

    companion object {
        const val BASE_URL = "https://anapioficeandfire.com/api"
    }
}
