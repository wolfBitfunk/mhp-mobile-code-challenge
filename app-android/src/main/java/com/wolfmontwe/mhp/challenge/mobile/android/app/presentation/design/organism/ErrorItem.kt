/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.organism

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wolfmontwe.mhp.challenge.mobile.android.app.R
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.atom.PrimaryTextButton
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.molecule.CardBody
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.molecule.CardHeader
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainThemeDimension
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainThemeWithSurface

@Composable
fun ErrorItem(
    title: String,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    testTagName: String = "ErrorItem",
) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = MainThemeDimension.spacingDouble,
                vertical = MainThemeDimension.spacingSingle
            )
            .fillMaxWidth()
            .testTag(testTagName)
            .then(modifier),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MainThemeDimension.spacingDouble)
                .background(MaterialTheme.colorScheme.errorContainer),
        ) {
            CardHeader(
                title = title,
            )
            CardBody(
                text = description,
                modifier = Modifier.padding(top = MainThemeDimension.spacingSingle)
            )
            PrimaryTextButton(
                text = stringResource(id = R.string.error_item_button_label),
                onClick = onClick,
                modifier = Modifier
                    .padding(top = MainThemeDimension.spacingDouble),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError,
                )
            )
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
fun ErrorItemPreview() {
    MainThemeWithSurface {
        ErrorItem("Title", "Description", {})
    }
}
