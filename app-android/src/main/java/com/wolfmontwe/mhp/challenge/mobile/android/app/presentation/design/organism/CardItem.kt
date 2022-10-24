/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.organism

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
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.molecule.CardHeader
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainThemeDimension
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainThemeWithSurface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    testTagName: String = "CardItem",
) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = MainThemeDimension.spacingDouble,
                vertical = MainThemeDimension.spacingSingle
            )
            .testTag(testTagName)
            .then(modifier),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MainThemeDimension.spacingDouble)
        ) {
            CardHeader(title)
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
fun CardItemPreview() {
    MainThemeWithSurface {
        CardItem("Title", {})
    }
}
