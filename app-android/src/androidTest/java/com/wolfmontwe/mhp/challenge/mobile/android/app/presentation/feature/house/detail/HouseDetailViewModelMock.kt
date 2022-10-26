/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HouseDetailViewModelMock(
    initialViewState: HouseDetailViewState = HouseDetailViewState()
) : HouseDetailContract.ViewModel, ViewModel() {

    private val _state: MutableStateFlow<HouseDetailViewState> = MutableStateFlow(initialViewState)
    override val state: StateFlow<HouseDetailViewState> = _state.asStateFlow()

    var recordedLoadItem: Boolean = false
        private set

    override fun loadItem() {
        recordedLoadItem = true
    }
}
