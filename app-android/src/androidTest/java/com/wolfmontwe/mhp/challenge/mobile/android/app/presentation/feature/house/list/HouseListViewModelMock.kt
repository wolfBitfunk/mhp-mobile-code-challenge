/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HouseListViewModelMock(
    initialViewState: HouseListViewState = HouseListViewState()
) : HouseListContract.ViewModel, ViewModel() {

    private val _state: MutableStateFlow<HouseListViewState> = MutableStateFlow(initialViewState)
    override val state: StateFlow<HouseListViewState> = _state.asStateFlow()

    var recordedLoadMoreItems: Boolean = false
        private set

    var recorededReset: Boolean = false
        private set

    override fun loadMoreItems() {
        recordedLoadMoreItems = true
    }

    override fun reset() {
        recorededReset = true
    }
}
