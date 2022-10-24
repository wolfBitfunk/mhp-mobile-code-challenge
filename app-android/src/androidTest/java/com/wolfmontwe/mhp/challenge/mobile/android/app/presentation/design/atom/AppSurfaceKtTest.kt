/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.atom

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.assertScreenshotMatches
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.setThemedScreenshotContent
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.setThemedSizedContent
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class AppSurfaceKtTest {

    @Test
    fun should_fill_screen_fully() = runComposeUiTest {
        // GIVEN
        val height = 480.dp
        val width = 300.dp
        setThemedSizedContent(height, width) {
            AppSurface(
                modifier = Modifier.fillMaxSize(),
                content = {},
            )
        }

        // WHEN
        val view = onNodeWithTag(testTagName)

        // THEN
        view.assertIsDisplayed()
            .assertHeightIsEqualTo(height)
            .assertWidthIsEqualTo(width)
    }

    @Test
    fun should_match_design_specification() = runComposeUiTest {
        // GIVEN
        setThemedScreenshotContent {
            AppSurface(
                modifier = Modifier.fillMaxSize(),
                content = {},
            )
        }

        // WHEN
        val view = onNodeWithTag(testTagName)

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("atom", testTagName)
    }

    private companion object {
        const val testTagName = "AppSurface"
    }
}
