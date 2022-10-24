/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.molecule

import android.content.res.Configuration
import androidx.compose.foundation.magnifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.atom.BodyMedium
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainTheme

@Composable
fun CardBody(
    text: String,
    modifier: Modifier = Modifier,
    testTagName: String = "CardBody",
) {
    BodyMedium(
        text = text,
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
fun CardBodyPreview() {
    MainTheme {
        CardBody("CardBody")
    }
}
