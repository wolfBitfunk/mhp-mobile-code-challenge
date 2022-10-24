/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.Repository
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.Service
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.UseCase
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House

class GetHousesPaginatedUseCase(
    private val repository: Repository.Houses,
    private val pagination: Service.Pagination
) : UseCase.GetHousesPaginated {

    override suspend fun getHouses(): Result<List<House>> {
        val result = repository.getHouses(
            page = pagination.nextPage(),
            pageSize = pagination.pageSize(),
        )
        if (result.isFailure()) {
            pagination.reset()
        }
        return result
    }

    override suspend fun reset() {
        pagination.reset()
    }
}
