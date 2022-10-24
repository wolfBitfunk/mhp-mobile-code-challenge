/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.molecule

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.assertScreenshotMatches
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.setThemedScreenshotContent
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class CardHeaderKtTest {

    @Test
    fun should_match_design_specification() = runComposeUiTest {
        // GIVEN
        setThemedScreenshotContent {
            CardHeader(title = "CardHeader")
        }

        // WHEN
        val view = onNodeWithTag("CardHeader")

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("molecule", "CardHeader")
    }
}
