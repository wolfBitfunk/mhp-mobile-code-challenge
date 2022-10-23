/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.mapper

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Mapper
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier

class IdentifierMapperMock : Mapper.Id {

    var answerMapToDomainObject: () -> Result<Identifier> = {
        throw IllegalArgumentException("answerMapToDomainList not defined")
    }

    var recordedIdentifier: String? = null
        private set

    override fun mapToDomain(from: String): Result<Identifier> {
        recordedIdentifier = from
        return answerMapToDomainObject()
    }
}
