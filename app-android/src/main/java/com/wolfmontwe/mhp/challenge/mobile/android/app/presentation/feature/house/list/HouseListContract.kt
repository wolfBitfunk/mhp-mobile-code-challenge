/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

import androidx.lifecycle.ViewModelProvider
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import kotlinx.coroutines.flow.StateFlow

interface HouseListContract {

    interface ViewModel {
        val state: StateFlow<HouseListViewState>

        fun loadMoreItems()
        fun reset()
    }

    interface ViewModelFactory : ViewModelProvider.Factory

    interface ViewState {
        val items: List<House>
        val isLoading: Boolean
        val error: String?
        val isPagingEnd: Boolean
    }
}
