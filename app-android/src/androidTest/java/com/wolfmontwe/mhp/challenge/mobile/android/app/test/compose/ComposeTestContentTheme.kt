/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainThemeWithSurface

@OptIn(ExperimentalTestApi::class)
fun ComposeUiTest.setThemedContent(
    content: @Composable () -> Unit
) {
    setContent {
        MainThemeWithSurface {
            content()
        }
    }
}

@OptIn(ExperimentalTestApi::class)
fun ComposeUiTest.setThemedSizedContent(
    height: Dp,
    width: Dp,
    content: @Composable () -> Unit
) {
    setContent {
        MainThemeWithSurface {
            Box(
                modifier = Modifier
                    .height(height)
                    .width(width)
            ) {
                content()
            }
        }
    }
}

@OptIn(ExperimentalTestApi::class)
fun ComposeUiTest.setThemedScreenshotContent(
    content: @Composable () -> Unit
) {
    setContent {
        MainThemeWithSurface(
            darkTheme = false,
            dynamicColor = false,
        ) {
            Box(
                modifier = Modifier
                    .height(800.dp)
                    .width(480.dp)
            ) {
                content()
            }
        }
    }
}
