/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.mapper

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Mapper
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier
import java.net.URL

class IdentifierMapper : Mapper.Id {
    override fun mapToDomain(from: String): Result<Identifier> {
        return try {
            val url = URL(from)
            val id = url.path.substringAfterLast("/")
            Result.success(Identifier(id = id))
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
