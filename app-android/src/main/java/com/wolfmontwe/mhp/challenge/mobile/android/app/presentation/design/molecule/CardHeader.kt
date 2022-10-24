/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.molecule

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.atom.TitleMedium
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainThemeWithSurface

@Composable
fun CardHeader(
    title: String,
    modifier: Modifier = Modifier,
    testTagName: String = "CardHeader",
) {
    TitleMedium(
        text = title,
        modifier = Modifier
            .then(modifier),
        testTagName = testTagName,
    )
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
fun CardHeaderPreview() {
    MainThemeWithSurface {
        CardHeader("CardHeader")
    }
}
