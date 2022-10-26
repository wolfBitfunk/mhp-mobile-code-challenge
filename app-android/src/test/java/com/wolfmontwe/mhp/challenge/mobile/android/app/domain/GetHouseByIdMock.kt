/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.UseCase
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier

class GetHouseByIdMock : UseCase.GetHouseById {

    var answerGetHouse: () -> Result<House> = {
        throw IllegalArgumentException("answerGetHouse not defined")
    }

    var recordedIdentifier: Identifier? = null
        private set

    override suspend fun getHouse(identifier: Identifier): Result<House> {
        recordedIdentifier = identifier
        return answerGetHouse()
    }
}
