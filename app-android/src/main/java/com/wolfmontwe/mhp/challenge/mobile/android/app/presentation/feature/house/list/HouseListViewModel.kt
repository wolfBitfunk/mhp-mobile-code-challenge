/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.UseCase
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.map
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HouseListViewModel(
    private val useCaseGetHousesPaginated: UseCase.GetHousesPaginated,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    initialViewState: HouseListViewState = HouseListViewState()
) : HouseListContract.ViewModel, ViewModel() {

    private val _state: MutableStateFlow<HouseListViewState> = MutableStateFlow(initialViewState)
    override val state: StateFlow<HouseListViewState> = _state.asStateFlow()

    init {
        loadMoreItems()
    }

    override fun loadMoreItems() {
        if (_state.value.isLoading || _state.value.isPagingEnd) return
        else {
            changeStateLoading(true)
            viewModelScope.launch(ioDispatcher) {
                val result: Result<List<House>> = useCaseGetHousesPaginated.getHouses()

                result.map(
                    success = { changeStateItems(it.data) },
                    failure = { changeStateError(it.exception) }
                )
            }
            changeStateLoading(false)
        }
    }

    override fun reset() {
        viewModelScope.launch(ioDispatcher) {
            useCaseGetHousesPaginated.reset()
        }
        changeStateReset()
    }

    private fun changeStateLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading)
    }

    private fun changeStateItems(items: List<House>) {
        _state.value = _state.value.copy(
            items = _state.value.items + items,
            isPagingEnd = items.isEmpty(),
        )
    }

    private fun changeStateError(exception: Exception?) {
        if (exception != null) {
            Log.e(this::class.simpleName, "Loading failed: ${exception.message}", exception)
        }
        _state.value = _state.value.copy(
            error = exception?.message
        )
    }

    private fun changeStateReset() {
        _state.value = _state.value.copy(
            items = emptyList(),
            isPagingEnd = false,
            isLoading = false,
            error = null,
        )
    }
}
