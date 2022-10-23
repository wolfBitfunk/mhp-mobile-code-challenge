/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.atom

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.assertScreenshotMatches
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.setThemedScreenshotContent
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class HeadlineTextKtTest {

    @Test
    fun should_match_design_specification_for_large() = runComposeUiTest {
        // GIVEN
        setThemedScreenshotContent {
            HeadlineLarge(text = "HeadlineLarge")
        }

        // WHEN
        val view = onNodeWithTag("HeadlineLarge")

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("atom", "HeadlineLarge")
    }

    @Test
    fun should_match_design_specification_for_medium() = runComposeUiTest {
        // GIVEN
        setThemedScreenshotContent {
            HeadlineMedium(text = "HeadlineMedium")
        }

        // WHEN
        val view = onNodeWithTag("HeadlineMedium")

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("atom", "HeadlineMedium")
    }

    @Test
    fun should_match_design_specification() = runComposeUiTest {
        // GIVEN
        setThemedScreenshotContent {
            HeadlineSmall(text = "HeadlineSmall")
        }

        // WHEN
        val view = onNodeWithTag("HeadlineSmall")

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("atom", "HeadlineSmall")
    }
}
