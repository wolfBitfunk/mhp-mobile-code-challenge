/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

import androidx.lifecycle.ViewModel
import com.wolfmontwe.mhp.challenge.mobile.android.app.di.Injector
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.UseCase

class HouseListViewModelFactory(
    private val useCaseGetHousesPaginated: UseCase.GetHousesPaginated = Injector.domain.useCaseGetHousesPaginated,
) : HouseListContract.ViewModelFactory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HouseListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HouseListViewModel(
                useCaseGetHousesPaginated = useCaseGetHousesPaginated
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
