/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import kotlin.test.Test

class HouseListViewStateTest {

    @Test
    fun `SHOULD implement contract`() {
        HouseListViewState() isOfType HouseListContract.ViewState::class
    }

    @Test
    fun `SHOULD set right defaults`() {
        // GIVEN
        val expected = HouseListViewState(
            items = emptyList(),
            isLoading = false,
            error = null,
            isPagingEnd = false
        )

        // WHEN
        val state = HouseListViewState()

        // THEN
        state mustEqual expected
    }
}
