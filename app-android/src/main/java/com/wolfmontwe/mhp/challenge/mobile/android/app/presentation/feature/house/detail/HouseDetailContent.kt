/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wolfmontwe.mhp.challenge.mobile.android.app.R
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainTheme
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainThemeDimension

@Composable
fun HouseDetailContent(
    house: House?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MainThemeDimension.spacingDouble)
            .then(modifier)
            .testTag("HouseDetailContent"),
    ) {
        if (house != null) {
            HouseDetailContentRowItem(
                label = stringResource(id = R.string.house_detail_content_row_label_name),
                text = house.name.substringAfter("House ")
            )
            HouseDetailContentRowItem(
                label = stringResource(id = R.string.house_detail_content_row_label_region),
                text = house.region
            )
            HouseDetailContentRowItem(
                label = stringResource(id = R.string.house_detail_content_row_label_coat_of_arms),
                text = house.coatOfArms
                    .ifEmpty { stringResource(id = R.string.house_detail_content_row_text_not_available) }
            )
            HouseDetailContentRowItem(
                label = stringResource(id = R.string.house_detail_content_row_label_words),
                text = house.words
                    .ifEmpty { stringResource(id = R.string.house_detail_content_row_text_not_available) }
            )
            val titles = if (house.titles.isEmpty()) {
                stringResource(id = R.string.house_detail_content_row_text_no_titles)
            } else {
                house.titles.joinToString("\n")
            }
            HouseDetailContentRowItem(
                label = stringResource(id = R.string.house_detail_content_row_label_titles),
                text = titles
            )
            HouseDetailContentRowItem(
                label = stringResource(id = R.string.house_detail_content_row_label_seats),
                text = (
                    if (house.seats.isNotEmpty()) house.seats.joinToString("\n")
                    else stringResource(id = R.string.house_detail_content_row_text_no_seats)
                    )
            )
            HouseDetailContentRowItem(
                label = stringResource(id = R.string.house_detail_content_row_label_members),
                text = "${house.members}"
            )
            HouseDetailContentRowItem(
                label = stringResource(id = R.string.house_detail_content_row_label_founded),
                text = house.founded
                    .ifEmpty { stringResource(id = R.string.house_detail_content_row_text_not_available) }
            )
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
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
fun HouseDetailContentPreview() {
    MainTheme {
        HouseDetailContent(
            house = House(
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
        )
    }
}
