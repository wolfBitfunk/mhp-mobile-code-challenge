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
fun LabelLarge(
    text: String,
    modifier: Modifier = Modifier,
    testTagName: String = "LabelLarge",
) {
    Text(
        modifier = Modifier
            .testTag(testTagName)
            .then(modifier),
        text = text,
        style = MaterialTheme.typography.labelLarge
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
fun LabelLargePreview() {
    MainTheme {
        LabelLarge("LabelLarge")
    }
}

@Composable
fun LabelMedium(
    text: String,
    modifier: Modifier = Modifier,
    testTagName: String = "LabelMedium",
) {
    Text(
        modifier = Modifier
            .testTag(testTagName)
            .then(modifier),
        text = text,
        style = MaterialTheme.typography.labelMedium
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
fun LabelMediumPreview() {
    MainTheme {
        LabelMedium("LabelMedium")
    }
}

@Composable
fun LabelSmall(
    text: String,
    modifier: Modifier = Modifier,
    testTagName: String = "LabelSmall",
) {
    Text(
        modifier = Modifier
            .testTag(testTagName)
            .then(modifier),
        text = text,
        style = MaterialTheme.typography.labelSmall
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
fun LabelSmallPreview() {
    MainTheme {
        LabelSmall("LabelSmall")
    }
}
