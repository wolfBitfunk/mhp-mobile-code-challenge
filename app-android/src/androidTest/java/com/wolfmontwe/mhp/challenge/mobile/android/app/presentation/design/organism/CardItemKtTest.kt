/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.organism

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
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class CardItemKtTest {

    @Test
    fun should_be_clickable_and_show_text_in_uppercase() = runComposeUiTest {
        // Given
        val text = "text"

        setThemedContent {
            CardItem(
                title = text,
                onClick = { }
            )
        }

        // When
        val button = onNodeWithTag(testTagName)

        // Then
        button.assertIsDisplayed()
            .assertHasClickAction()
            .assertTextEquals(text)
    }

    @Test
    fun should_execute_onClick_when_clicked() = runComposeUiTest {
        // Given
        var clickExecuted = false

        setThemedContent {
            CardItem(
                title = "CardTitle",
                onClick = { clickExecuted = true }
            )
        }

        // When
        val button = onNodeWithTag(testTagName)
            .performClick()

        // Then
        button.assertIsDisplayed()
            .assertHasClickAction()

        clickExecuted mustEqual true
    }

    @Test
    fun should_match_design_specification() = runComposeUiTest {
        // Given
        setThemedScreenshotContent {
            CardItem(
                title = "CardTitle",
                onClick = { },
            )
        }

        // When
        val button = onNodeWithTag(testTagName)

        // Then
        button.assertIsDisplayed()
            .assertScreenshotMatches("organism", testTagName)
    }

    companion object {
        const val testTagName = "CardItem"
    }
}
