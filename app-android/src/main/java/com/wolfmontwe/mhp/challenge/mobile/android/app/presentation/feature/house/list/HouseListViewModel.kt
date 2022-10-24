/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

import androidx.lifecycle.ViewModel
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.UseCase

class HouseListViewModel(
    private val useCaseGetHousesPaginated: UseCase.GetHousesPaginated
) : HouseListContract.ViewModel, ViewModel() {
    override fun loadMoreItems() {
    }

    override fun retry() {
    }
}
