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
fun BodyLarge(
    text: String,
    modifier: Modifier = Modifier,
    testTagName: String = "BodyLarge",
) {
    Text(
        modifier = Modifier
            .testTag(testTagName)
            .then(modifier),
        text = text,
        style = MaterialTheme.typography.bodyLarge
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
fun BodyLargePreview() {
    MainThemeWithSurface {
        BodyLarge("BodyLarge")
    }
}

@Composable
fun BodyMedium(
    text: String,
    modifier: Modifier = Modifier,
    testTagName: String = "BodyMedium",
) {
    Text(
        modifier = Modifier
            .testTag(testTagName)
            .then(modifier),
        text = text,
        style = MaterialTheme.typography.bodyMedium
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
fun BodyMediumPreview() {
    MainThemeWithSurface {
        BodyMedium("BodyMedium")
    }
}

@Composable
fun BodySmall(
    text: String,
    modifier: Modifier = Modifier,
    testTagName: String = "BodySmall",
) {
    Text(
        modifier = Modifier
            .testTag(testTagName)
            .then(modifier),
        text = text,
        style = MaterialTheme.typography.bodySmall
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
fun BodySmallPreview() {
    MainThemeWithSurface {
        BodySmall("BodySmall")
    }
}
