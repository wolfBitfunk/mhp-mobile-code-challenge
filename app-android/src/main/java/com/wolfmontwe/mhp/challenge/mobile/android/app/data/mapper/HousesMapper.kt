/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.mapper

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Mapper
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Failure
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Success
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.map

class HousesMapper(
    private val idMapper: Mapper.Id
) : Mapper.Houses {

    override fun mapToDomain(from: HouseResponse): Result<House> {
        return when (val idResult = idMapper.mapToDomain(from.url)) {
            is Success -> mapToDomainWithId(idResult.data, from)
            is Failure -> idResult
        }
    }

    private fun mapToDomainWithId(identifier: Identifier, from: HouseResponse): Result<House> {
        return try {
            Result.success(
                House(
                    id = identifier,
                    name = from.name
                )
            )
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override fun mapToDomain(from: List<HouseResponse>): Result<List<House>> {
        return try {
            Result.success(
                from.map { mapToDomain(it) }
                    .map { result ->
                        result.map(
                            { it.data },
                            { throw it.exception }
                        )
                    }.toList()
            )
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
