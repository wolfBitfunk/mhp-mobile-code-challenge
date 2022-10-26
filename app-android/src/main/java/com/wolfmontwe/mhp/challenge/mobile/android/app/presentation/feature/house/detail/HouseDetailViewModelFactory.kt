/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.detail

import androidx.lifecycle.ViewModel
import com.wolfmontwe.mhp.challenge.mobile.android.app.di.Injector
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.UseCase

class HouseDetailViewModelFactory(
    private val houseId: String,
    private val useCaseGetHouseById: UseCase.GetHouseById = Injector.domain.useCaseGetHouseById,
) : HouseDetailContract.ViewModelFactory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HouseDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HouseDetailViewModel(
                houseId = houseId,
                useCaseGetHouseById = useCaseGetHouseById
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
