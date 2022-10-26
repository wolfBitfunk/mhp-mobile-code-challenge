/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainTheme

@Composable
fun HouseDetailContent(
    house: House?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .testTag("HouseDetailContent"),
    ) {
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
                name = "House name"
            ),
        )
    }
}
