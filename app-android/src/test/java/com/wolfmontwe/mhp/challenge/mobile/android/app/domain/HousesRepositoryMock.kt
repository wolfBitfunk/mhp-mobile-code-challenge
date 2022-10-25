/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.Repository
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier

class HousesRepositoryMock : Repository.Houses {

    var answerGetHouses: () -> Result<List<House>> = {
        throw IllegalArgumentException("answerGetHouses not defined")
    }

    var answerGetHouse: () -> Result<House> = {
        throw IllegalArgumentException("answerGetHouse not defined")
    }

    var recordedPage: Int = -1
        private set

    var recordedPageSize: Int = -1
        private set

    var recordedIdentifier: Identifier? = null
        private set

    override suspend fun getHouses(page: Int, pageSize: Int): Result<List<House>> {
        recordedPage = page
        recordedPageSize = pageSize
        return answerGetHouses()
    }

    override suspend fun getHouse(identifier: Identifier): Result<House> {
        recordedIdentifier = identifier
        return answerGetHouse()
    }
}
