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
class TitleTextKtTest {

    @Test
    fun should_match_design_specification_for_large() = runComposeUiTest {
        // GIVEN
        setThemedScreenshotContent {
            TitleLarge(text = "TitleLarge")
        }

        // WHEN
        val view = onNodeWithTag("TitleLarge")

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("atom", "TitleLarge")
    }

    @Test
    fun should_match_design_specification_for_medium() = runComposeUiTest {
        // GIVEN
        setThemedScreenshotContent {
            TitleMedium(text = "TitleMedium")
        }

        // WHEN
        val view = onNodeWithTag("TitleMedium")

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("atom", "TitleMedium")
    }

    @Test
    fun should_match_design_specification() = runComposeUiTest {
        // GIVEN
        setThemedScreenshotContent {
            TitleSmall(text = "TitleSmall")
        }

        // WHEN
        val view = onNodeWithTag("TitleSmall")

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("atom", "TitleSmall")
    }
}
