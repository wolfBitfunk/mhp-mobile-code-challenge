/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.detail

import androidx.lifecycle.ViewModelProvider
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier

interface HouseDetailContract {

    interface ViewModel {
        fun loadItem()
    }

    interface ViewModelFactory : ViewModelProvider.Factory

    data class ViewState(
        val identifier: Identifier? = null,
        val item: House? = null,
        val isLoading: Boolean = false,
        val error: String? = null,
    )
}
