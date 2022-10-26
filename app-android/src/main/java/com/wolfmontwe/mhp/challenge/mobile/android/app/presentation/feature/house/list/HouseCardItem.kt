/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.molecule.CardHeader
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainTheme
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainThemeDimension

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HouseCardItem(
    house: House,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    testTagName: String = "HouseCardItem",
) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = MainThemeDimension.spacingDouble,
                vertical = MainThemeDimension.spacingSingle
            )
            .then(modifier)
            .testTag(testTagName),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MainThemeDimension.spacingDouble)
        ) {
            val name = house.name.substringAfter("House ")
            CardHeader("${house.id.value}. $name")
        }
    }
}

@Preview(
    name = "Light preview",
    showBackground = true,
)
@Preview(
    name = "Dark preview",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun HouseCardItemPreview() {
    MainTheme {
        HouseCardItem(
            House(
                id = Identifier("123"),
                name = "TestName",
                region = "TestRegion",
                coatOfArms = "TestCoatOfArms",
                words = "TestWords",
                titles = listOf("TestTitle1", "TestTitle2"),
                seats = listOf("TestSeat1", "TestSeat2"),
                members = 11,
                founded = "TestFounded"
            ),
            {}
        )
    }
}
