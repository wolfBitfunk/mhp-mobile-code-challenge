/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.atom

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.assertScreenshotMatches
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.setThemedContent
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.setThemedScreenshotContent
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class PrimaryTextButtonKtTest {

    @Test
    fun should_be_clickable_and_show_text_in_uppercase() = runComposeUiTest {
        // Given
        val text = "text"

        setThemedContent {
            PrimaryTextButton(
                text = text,
                onClick = { }
            )
        }

        // When
        val view = onNodeWithTag(testTagName)

        // Then
        view.assertIsDisplayed()
            .assertHasClickAction()
            .assertTextEquals("TEXT")
    }

    @Test
    fun should_execute_onClick_when_clicked() = runComposeUiTest {
        // Given
        val text = "text"
        var clickExecuted = false

        setThemedContent {
            PrimaryTextButton(
                text = text,
                onClick = { clickExecuted = true }
            )
        }

        // When
        val view = onNodeWithTag(testTagName)
            .performClick()

        // Then
        view.assertIsDisplayed()
            .assertHasClickAction()

        clickExecuted mustEqual true
    }

    @Test
    fun should_match_design_specification() = runComposeUiTest {
        // Given
        val text = "text"

        setThemedScreenshotContent {
            PrimaryTextButton(
                text = text,
                onClick = { }
            )
        }

        // When
        val view = onNodeWithTag(testTagName)

        // Then
        view.assertIsDisplayed()
            .assertScreenshotMatches("atom", testTagName)
    }

    private companion object {
        const val testTagName = "PrimaryTextButton"
    }
}
