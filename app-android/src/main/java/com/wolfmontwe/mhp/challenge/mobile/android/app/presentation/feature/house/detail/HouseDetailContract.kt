/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.detail

import androidx.lifecycle.ViewModelProvider
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier
import kotlinx.coroutines.flow.StateFlow

interface HouseDetailContract {

    interface ViewModel {
        val state: StateFlow<HouseDetailViewState>

        fun loadItem()
    }

    interface ViewModelFactory : ViewModelProvider.Factory

    interface ViewState {
        val identifier: Identifier?
        val item: House?
        val isLoading: Boolean
        val error: String?
    }
}
