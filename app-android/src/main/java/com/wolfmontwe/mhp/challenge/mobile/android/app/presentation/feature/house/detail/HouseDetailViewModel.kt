/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.detail

import androidx.lifecycle.ViewModel
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.UseCase

class HouseDetailViewModel(
    private val houseId: String,
    private val useCaseGetHouseById: UseCase.GetHouseById,
) : HouseDetailContract.ViewModel, ViewModel() {
}
