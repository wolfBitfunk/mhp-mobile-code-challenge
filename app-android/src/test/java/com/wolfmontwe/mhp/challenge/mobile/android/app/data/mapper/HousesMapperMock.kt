/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.mapper

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Mapper
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House

class HousesMapperMock : Mapper.Houses {

    var answerMapToDomainList: () -> Result<List<House>> = {
        throw IllegalArgumentException("answerMapToDomainList not defined")
    }

    var answerMapToDomainObject: () -> Result<House> = {
        throw IllegalArgumentException("answerMapToDomainList not defined")
    }

    var recordedHouseResponse: HouseResponse? = null
        private set

    var recordedHouseResponseList: List<HouseResponse>? = null
        private set

    override fun mapToDomain(from: HouseResponse): Result<House> {
        recordedHouseResponse = from
        return answerMapToDomainObject()
    }

    override fun mapToDomain(from: List<HouseResponse>): Result<List<House>> {
        recordedHouseResponseList = from
        return answerMapToDomainList()
    }
}
