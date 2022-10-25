/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.DataSource
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier

class HousesRepository(
    private val remote: DataSource.Houses.Remote
) : DomainContract.Repository.Houses {

    override suspend fun getHouses(page: Int, pageSize: Int): Result<List<House>> {
        return remote.getHouses(page = page, pageSize = pageSize)
    }

    override suspend fun getHouse(identifier: Identifier): Result<House> {
        return remote.getHouse(identifier)
    }
}
