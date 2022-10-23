/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.DataSource
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House

class HousesRemoteDataSourceMock : DataSource.Houses.Remote {

    var answerGetHouses: () -> Result<List<House>> = {
        throw IllegalArgumentException("answerMapToDomainList not defined")
    }

    var recordedHousesPage: Int? = null
        private set

    var recordedHousesPageSize: Int? = null
        private set

    override suspend fun getHouses(page: Int, pageSize: Int): Result<List<House>> {
        recordedHousesPage = page
        recordedHousesPageSize = pageSize
        return answerGetHouses()
    }
}
