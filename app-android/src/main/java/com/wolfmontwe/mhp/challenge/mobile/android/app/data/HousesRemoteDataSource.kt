/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.DataSource
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Mapper
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Failure
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Success
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier

class HousesRemoteDataSource(
    private val api: Network.IceAndFireApi,
    private val mapper: Mapper.Houses,
) : DataSource.Houses.Remote {

    override suspend fun getHouses(page: Int, pageSize: Int): Result<List<House>> {
        val result = api.loadHouses(page = page, pageSize = pageSize)
        return mapResultToDomain(result) { mapper.mapToDomain(it) }
    }

    override suspend fun getHouse(identifier: Identifier): Result<House> {
        val result = api.loadHouse(identifier.value)
        return mapResultToDomain(result) { mapper.mapToDomain(it) }
    }

    private fun <IN, OUT> mapResultToDomain(
        result: Result<IN>,
        mapper: (IN) -> Result<OUT>
    ): Result<OUT> {
        return when (result) {
            is Success -> mapper(result.data)
            is Failure -> result
        }
    }
}
