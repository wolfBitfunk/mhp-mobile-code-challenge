/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.atom

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PrimaryTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    testTagName: String = "PrimaryTextButton",
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .then(modifier)
            .testTag(testTagName),
        colors = colors,
    ) {
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
fun PrimaryTextButtonPreview() {
    PrimaryTextButton("Press here", {})
}
