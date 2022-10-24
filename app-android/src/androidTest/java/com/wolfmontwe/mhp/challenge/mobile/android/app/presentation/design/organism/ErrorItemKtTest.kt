/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.organism

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import com.wolfmontwe.mhp.challenge.mobile.android.app.R
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.assertScreenshotMatches
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.setThemedContent
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.setThemedScreenshotContent
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class ErrorItemKtTest {

    @Test
    fun should_be_clickable_and_show_text_in_uppercase() = runComposeUiTest {
        // Given
        val title = "text"
        val description = "description"
        lateinit var buttonLabel: String
        var clickExecuted = false

        setThemedContent {
            buttonLabel = stringResource(id = R.string.error_item_button_label)

            ErrorItem(
                title = title,
                description = description,
                onClick = { clickExecuted = true }
            )
        }

        // When
        val view = onNodeWithTag(testTagName)

        // Then
        view.assertIsDisplayed()
            .assertHasNoClickAction()

        onNodeWithTag("CardHeader")
            .assertHasNoClickAction()
            .assertTextEquals(title)

        onNodeWithTag("CardBody")
            .assertHasNoClickAction()
            .assertTextEquals(description)

        // Button
        onNodeWithTag("PrimaryTextButton")
            .assertIsDisplayed()
            .assertHasClickAction()
            .assertTextEquals(buttonLabel.uppercase())
            .performClick()

        clickExecuted mustEqual true
    }

    @Test
    fun should_match_design_specification() = runComposeUiTest {
        // GIVEN
        val title = "text"
        val description = "description"

        setThemedScreenshotContent {
            ErrorItem(
                title = title,
                description = description,
                onClick = { }
            )
        }

        // WHEN
        val view = onNodeWithTag(testTagName)

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("organism", testTagName)
    }

    private companion object {
        const val testTagName = "ErrorItem"
    }
}
