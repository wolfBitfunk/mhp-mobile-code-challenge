/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

@file:OptIn(ExperimentalTestApi::class)

package com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainThemeColor
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainTheme

fun ComposeUiTest.setThemedContent(
    content: @Composable () -> Unit
) {
    setContent {
        MainTheme {
            content()
        }
    }
}

fun ComposeUiTest.setThemedSizedContent(
    height: Dp,
    width: Dp,
    content: @Composable () -> Unit
) {
    setContent {
        MainTheme {
            Surface(
                color = MainThemeColor.debug
            ) {
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
}

fun ComposeUiTest.setThemedScreenshotContent(
    content: @Composable () -> Unit
) {
    setContent {
        MainTheme(darkTheme = false) {
            Surface(
                color = MainThemeColor.debug
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
}
