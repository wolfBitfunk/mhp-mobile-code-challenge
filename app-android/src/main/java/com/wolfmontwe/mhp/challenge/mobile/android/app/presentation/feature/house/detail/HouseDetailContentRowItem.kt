/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.atom.BodyLarge
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.atom.LabelLarge
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainTheme
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainThemeDimension

@Composable
fun HouseDetailContentRowItem(
    label: String,
    text: String
) {
    Row(
        modifier = Modifier.padding(vertical = MainThemeDimension.spacingSingle)
    ) {
        LabelLarge(
            text = label,
            modifier = Modifier
                .width(96.dp)
                .padding(end = MainThemeDimension.spacingSingle)
        )
        BodyLarge(text = text)
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
        HouseDetailContentRowItem(
            label = "Label",
            text = "Lorem ipsum"
        )
    }
}
