/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House

class HousesRepository : DomainContract.Repository.Houses {

    override suspend fun getHouses(page: Int, pageSize: Int): Result<List<House>> {
        TODO("Not yet implemented")
    }
}
