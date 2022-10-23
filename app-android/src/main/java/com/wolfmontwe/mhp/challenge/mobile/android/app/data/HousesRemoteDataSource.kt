/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.DataSource
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result

class HousesRemoteDataSource(
    private val api: Network.IceAndFireApi
) : DataSource.Houses.Remote {

    override suspend fun getHouses(page: Int, pageSize: Int): Result<List<HouseResponse>> {
        return api.loadHouses(page = page, pageSize = pageSize)
    }
}
