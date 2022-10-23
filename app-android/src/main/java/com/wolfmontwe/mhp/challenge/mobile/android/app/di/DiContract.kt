/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.di

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.UseCase

interface DiContract {

    val domain: Domain

    interface Domain {
        val useCaseGetHousesPaginated: UseCase.GetHousesPaginated
    }
}
