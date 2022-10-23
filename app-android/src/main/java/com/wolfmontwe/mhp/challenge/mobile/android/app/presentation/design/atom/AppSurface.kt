/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.atom

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppSurface(
    modifier: Modifier = Modifier,
    testTagName: String = "AppSurface",
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .testTag(testTagName)
            .then(modifier),
        color = MaterialTheme.colorScheme.background,
        content = content
    )
}

@Preview
@Composable
fun AppSurfacePreview() {
    AppSurface { }
}
