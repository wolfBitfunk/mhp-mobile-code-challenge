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
class BodyTextKtTest {

    @Test
    fun should_match_design_specification_for_large() = runComposeUiTest {
        // GIVEN
        setThemedScreenshotContent {
            BodyLarge(text = "BodyLarge")
        }

        // WHEN
        val view = onNodeWithTag("BodyLarge")

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("atom", "BodyLarge")
    }

    @Test
    fun should_match_design_specification_for_medium() = runComposeUiTest {
        // GIVEN
        setThemedScreenshotContent {
            BodyMedium(text = "BodyMedium")
        }

        // WHEN
        val view = onNodeWithTag("BodyMedium")

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("atom", "BodyMedium")
    }

    @Test
    fun should_match_design_specification() = runComposeUiTest {
        // GIVEN
        setThemedScreenshotContent {
            BodySmall(text = "BodySmall")
        }

        // WHEN
        val view = onNodeWithTag("BodySmall")

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("atom", "BodySmall")
    }
}
