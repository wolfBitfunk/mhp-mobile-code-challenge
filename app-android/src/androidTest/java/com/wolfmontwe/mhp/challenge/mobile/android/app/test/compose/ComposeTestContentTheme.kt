/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.unit.dp
import com.wolfmontwe.mhp.challenge.mobile.android.app.ui.theme.MainColors
import com.wolfmontwe.mhp.challenge.mobile.android.app.ui.theme.MainTheme

fun ComposeContentTestRule.setThemedContent(
    content: @Composable () -> Unit
) {
    setContent {
        MainTheme {
            content()
        }
    }
}

fun ComposeContentTestRule.setThemedScreenshotContent(
    content: @Composable () -> Unit
) {
    setContent {
        MainTheme {
            Surface(
                color = MainColors.debug
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
