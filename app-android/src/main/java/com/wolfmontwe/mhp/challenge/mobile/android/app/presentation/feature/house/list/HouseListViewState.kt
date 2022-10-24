/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House

data class HouseListViewState(
    override val items: List<House> = emptyList(),
    override val isLoading: Boolean = false,
    override val error: String? = null,
    override val isPagingEnd: Boolean = false,
) : HouseListContract.ViewState
