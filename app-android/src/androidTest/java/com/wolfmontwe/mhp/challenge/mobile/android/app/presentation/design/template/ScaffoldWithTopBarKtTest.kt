/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.template

import androidx.compose.material3.Text
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.assertScreenshotMatches
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.setThemedContent
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.setThemedScreenshotContent
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class ScaffoldWithTopBarKtTest {

    @Test
    fun should_show_title_in_top_bar_and_content() = runComposeUiTest {
        // GIVEN
        val title = "Title"
        setThemedContent {
            ScaffoldWithTopBar(title = title) {
                Text(text = "Content")
            }
        }

        // WHEN
        val view = onNodeWithTag(testTagName)

        // THEN
        view.assertIsDisplayed()
        view.onChildAt(0).assertTextEquals("Content")
        view.onChildAt(1).onChild().assertTextEquals(title)
    }

    @Test
    fun should_match_design_specification() = runComposeUiTest {
        // GIVEN
        setThemedScreenshotContent {
            ScaffoldWithTopBar(title = "title") {}
        }

        // WHEN
        val view = onNodeWithTag(testTagName)

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("template", testTagName)
    }

    companion object {
        const val testTagName = "ScaffoldWithTopBar"
    }
}
