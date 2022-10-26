/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.test.fixture

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier

object HouseTestFixture {

    val EXAMPLE_ID = "378"

    val EXAMPLE = House(
        id = Identifier("378"),
        name = "House Targaryen of King's Landing",
    )
    val EXAMPLE_LIST: List<House> = listOf(EXAMPLE, EXAMPLE)
}
