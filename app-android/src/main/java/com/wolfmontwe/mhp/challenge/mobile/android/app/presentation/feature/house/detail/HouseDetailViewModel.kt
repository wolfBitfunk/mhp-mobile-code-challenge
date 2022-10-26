/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.UseCase
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.map
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HouseDetailViewModel(
    private val houseId: String,
    private val useCaseGetHouseById: UseCase.GetHouseById,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    initialViewState: HouseDetailViewState = HouseDetailViewState()
) : HouseDetailContract.ViewModel, ViewModel() {

    private val _state: MutableStateFlow<HouseDetailViewState> = MutableStateFlow(initialViewState)
    override val state: StateFlow<HouseDetailViewState> = _state.asStateFlow()

    init {
        changeStateIdentifier(Identifier(houseId))
        loadItem()
    }

    override fun loadItem() {
        if (_state.value.isLoading) return
        else {
            val identifier = _state.value.identifier
            if (identifier != null) {
                changeStateLoading(true)
                viewModelScope.launch(ioDispatcher) {
                    val result: Result<House> = useCaseGetHouseById.getHouse(identifier)

                    result.map(
                        success = { changeStateItem(it.data) },
                        failure = { changeStateError(it.exception) }
                    )
                }
                changeStateLoading(false)
            }
        }
    }

    private fun changeStateIdentifier(identifier: Identifier) {
        _state.value = _state.value.copy(
            identifier = identifier
        )
    }

    private fun changeStateItem(item: House) {
        _state.value = _state.value.copy(
            item = item,
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

    private fun changeStateLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading)
    }
}
