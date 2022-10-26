/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.detail

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier

data class HouseDetailViewState(
    override val identifier: Identifier? = null,
    override val item: House? = null,
    override val isLoading: Boolean = false,
    override val error: String? = null,
) : HouseDetailContract.ViewState
