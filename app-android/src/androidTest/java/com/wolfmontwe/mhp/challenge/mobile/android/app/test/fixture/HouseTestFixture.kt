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
        region = "The Crownlands",
        coatOfArms = "Sable, a dragon thrice-headed gules",
        words = "Fire and Blood",
        titles = listOf(
            "King of the Andals, the Rhoynar and the First Men",
            "Lord of the Seven Kingdoms",
            "Prince of Summerhall"
        ),
        seats = listOf(
            "Red Keep (formerly)",
            "Summerhall (formerly)"
        ),
        members = 101,
        founded = "House Targaryen: >114 BCHouse Targaryen of King's Landing:1 AC",
    )
    val EXAMPLE_LIST: List<House> = listOf(EXAMPLE, EXAMPLE)
}
