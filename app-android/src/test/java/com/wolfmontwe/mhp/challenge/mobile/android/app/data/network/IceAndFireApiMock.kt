/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.network

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.BookResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.CharacterResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result

class IceAndFireApiMock : Network.IceAndFireApi {

    var answerLoadHouses: () -> Result<List<HouseResponse>> = {
        throw IllegalArgumentException("answerLoadHouses not defined")
    }

    var answerLoadHouse: () -> Result<HouseResponse> = {
        throw IllegalArgumentException("answerLoadHouse not defined")
    }

    var recordedHousesPage: Int = -1
        private set

    var recordedHousesPageSize: Int = -1
        private set

    var recordedHouseId: String? = null
        private set

    override suspend fun loadHouses(page: Int, pageSize: Int): Result<List<HouseResponse>> {
        recordedHousesPage = page
        recordedHousesPageSize = pageSize
        return answerLoadHouses()
    }

    override suspend fun loadHouse(id: String): Result<HouseResponse> {
        recordedHouseId = id
        return answerLoadHouse()
    }

    override suspend fun loadCharacters(page: Int, pageSize: Int): Result<List<CharacterResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun loadCharacter(id: String): Result<CharacterResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun loadBooks(page: Int, pageSize: Int): Result<List<BookResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun loadBook(id: String): Result<BookResponse> {
        TODO("Not yet implemented")
    }
}
