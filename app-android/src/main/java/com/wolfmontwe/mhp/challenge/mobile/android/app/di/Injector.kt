/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.di

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.UseCase
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.GetHousesPaginatedUseCase

object Injector : DiContract {

    override val domain: DiContract.Domain = Domain

    internal object Domain : DiContract.Domain {
        override val useCaseGetHousesPaginated: UseCase.GetHousesPaginated
            get() = TODO("Not yet implemented")
    }
}
