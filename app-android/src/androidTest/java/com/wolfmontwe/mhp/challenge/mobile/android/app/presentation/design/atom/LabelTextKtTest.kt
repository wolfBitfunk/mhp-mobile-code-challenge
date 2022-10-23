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
class LabelTextKtTest {

    @Test
    fun should_match_design_specification_for_large() = runComposeUiTest {
        // GIVEN
        setThemedScreenshotContent {
            LabelLarge(text = "LabelLarge")
        }

        // WHEN
        val view = onNodeWithTag("LabelLarge")

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("atom", "LabelLarge")
    }

    @Test
    fun should_match_design_specification_for_medium() = runComposeUiTest {
        // GIVEN
        setThemedScreenshotContent {
            LabelMedium(text = "LabelMedium")
        }

        // WHEN
        val view = onNodeWithTag("LabelMedium")

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("atom", "LabelMedium")
    }

    @Test
    fun should_match_design_specification() = runComposeUiTest {
        // GIVEN
        setThemedScreenshotContent {
            LabelSmall(text = "LabelSmall")
        }

        // WHEN
        val view = onNodeWithTag("LabelSmall")

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("atom", "LabelSmall")
    }
}
