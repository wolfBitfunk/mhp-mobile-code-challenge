/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.Repository
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House

class HousesRepositoryMock : Repository.Houses {

    var answerGetHouses: () -> Result<List<House>> = {
        throw IllegalArgumentException("answerGet not defined")
    }

    var recordedPage: Int = -1
        private set

    var recordedPageSize: Int = -1
        private set

    override suspend fun getHouses(page: Int, pageSize: Int): Result<List<House>> {
        recordedPage = page
        recordedPageSize = pageSize
        return answerGetHouses()
    }
}
