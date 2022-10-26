/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.detail

import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import kotlin.test.Test

class HouseDetailViewStateTest {

    @Test
    fun `SHOULD implement contract`() {
        HouseDetailViewState() isOfType HouseDetailContract.ViewState::class
    }

    @Test
    fun `SHOULD set right defaults`() {
        // GIVEN
        val expected = HouseDetailViewState(
            identifier = null,
            item = null,
            isLoading = false,
            error = null,
        )

        // WHEN
        val state = HouseDetailViewState()

        // THEN
        state mustEqual expected
    }
}
