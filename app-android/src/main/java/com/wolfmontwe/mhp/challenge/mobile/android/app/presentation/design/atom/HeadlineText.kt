/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.atom

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainThemeWithSurface

@Composable
fun HeadlineLarge(
    text: String,
    modifier: Modifier = Modifier,
    testTagName: String = "HeadlineLarge",
) {
    Text(
        modifier = Modifier
            .testTag(testTagName)
            .then(modifier),
        text = text,
        style = MaterialTheme.typography.headlineLarge
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
fun HeadlineLargePreview() {
    MainThemeWithSurface {
        HeadlineLarge("HeadlineLarge")
    }
}

@Composable
fun HeadlineMedium(
    text: String,
    modifier: Modifier = Modifier,
    testTagName: String = "HeadlineMedium",
) {
    Text(
        modifier = Modifier
            .testTag(testTagName)
            .then(modifier),
        text = text,
        style = MaterialTheme.typography.headlineMedium
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
fun HeadlineMediumPreview() {
    MainThemeWithSurface {
        HeadlineMedium("HeadlineMedium")
    }
}

@Composable
fun HeadlineSmall(
    text: String,
    modifier: Modifier = Modifier,
    testTagName: String = "HeadlineSmall",
) {
    Text(
        modifier = Modifier
            .testTag(testTagName)
            .then(modifier),
        text = text,
        style = MaterialTheme.typography.headlineSmall
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
fun HeadlineSmallPreview() {
    MainThemeWithSurface {
        HeadlineSmall("HeadlineSmall")
    }
}
