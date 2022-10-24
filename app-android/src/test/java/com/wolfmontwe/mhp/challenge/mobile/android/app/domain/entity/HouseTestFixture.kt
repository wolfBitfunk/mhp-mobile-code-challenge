/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result

object HouseTestFixture {

    val EXAMPLE = House(
        id = Identifier("378"),
        name = "House Targaryen of King's Landing",
    )
    val EXAMPLE_LIST: List<House> = listOf(EXAMPLE, EXAMPLE)

    val RESULT_SUCCESS_EXAMPLE: Result<House> = Result.success(EXAMPLE)
    val RESULT_SUCCESS_EXAMPLE_LIST: Result<List<House>> = Result.success(EXAMPLE_LIST)
}
