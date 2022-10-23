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
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainTheme

@Composable
fun TitleLarge(
    text: String,
    modifier: Modifier = Modifier,
    testTagName: String = "TitleLarge",
) {
    Text(
        modifier = Modifier
            .testTag(testTagName)
            .then(modifier),
        text = text,
        style = MaterialTheme.typography.titleLarge
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
fun TitleLargePreview() {
    MainTheme {
        TitleLarge("TitleLarge")
    }
}

@Composable
fun TitleMedium(
    text: String,
    modifier: Modifier = Modifier,
    testTagName: String = "TitleMedium",
) {
    Text(
        modifier = Modifier
            .testTag(testTagName)
            .then(modifier),
        text = text,
        style = MaterialTheme.typography.titleMedium
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
fun TitleMediumPreview() {
    MainTheme {
        TitleMedium("TitleMedium")
    }
}

@Composable
fun TitleSmall(
    text: String,
    modifier: Modifier = Modifier,
    testTagName: String = "TitleSmall",
) {
    Text(
        modifier = Modifier
            .testTag(testTagName)
            .then(modifier),
        text = text,
        style = MaterialTheme.typography.titleSmall
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
fun TitleSmallPreview() {
    MainTheme {
        TitleSmall("TitleSmall")
    }
}
