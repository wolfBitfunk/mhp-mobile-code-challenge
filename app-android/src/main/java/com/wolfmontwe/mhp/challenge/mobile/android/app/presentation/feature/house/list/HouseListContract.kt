/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

import androidx.lifecycle.ViewModelProvider
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House

interface HouseListContract {

    interface ViewModel {
        fun loadMoreItems()
        fun retry()
    }

    interface ViewModelFactory : ViewModelProvider.Factory

    data class ViewState(
        val items: List<House> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null,
        val isPagingEnd: Boolean = false,
    )
}
