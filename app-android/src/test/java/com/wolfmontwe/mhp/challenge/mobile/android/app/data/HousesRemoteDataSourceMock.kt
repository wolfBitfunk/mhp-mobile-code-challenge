/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.DataSource
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier

class HousesRemoteDataSourceMock : DataSource.Houses.Remote {

    var answerGetHouses: () -> Result<List<House>> = {
        throw IllegalArgumentException("answerGetHouses not defined")
    }

    var answerGetHouse: () -> Result<House> = {
        throw IllegalArgumentException("answerGetHouse not defined")
    }

    var recordedHousesPage: Int? = null
        private set

    var recordedHousesPageSize: Int? = null
        private set

    var recordedIdentifier: Identifier? = null

    override suspend fun getHouses(page: Int, pageSize: Int): Result<List<House>> {
        recordedHousesPage = page
        recordedHousesPageSize = pageSize
        return answerGetHouses()
    }

    override suspend fun getHouse(identifier: Identifier): Result<House> {
        recordedIdentifier = identifier
        return answerGetHouse()
    }
}
