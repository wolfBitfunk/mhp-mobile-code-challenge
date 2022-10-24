/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.UseCase
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House

class GetHousesPaginatedMock : UseCase.GetHousesPaginated {

    var answerGetHouses: () -> Result<List<House>> = {
        throw IllegalArgumentException("answerGetHouse not defined")
    }

    var recordedGetHouses: Boolean = false
        private set

    var recordedReset: Boolean = false
        private set

    override suspend fun getHouses(): Result<List<House>> {
        recordedGetHouses = true
        return answerGetHouses()
    }

    override suspend fun reset() {
        recordedReset = true
    }
}
