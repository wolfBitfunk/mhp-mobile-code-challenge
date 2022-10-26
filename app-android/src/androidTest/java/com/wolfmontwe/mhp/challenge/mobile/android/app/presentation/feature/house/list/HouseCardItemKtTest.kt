/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.assertScreenshotMatches
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.setThemedContent
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.fixture.HouseTestFixture
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class HouseCardItemKtTest {

    @Test
    fun should_be_clickable_and_show_name() = runComposeUiTest {
        // Given
        var clickExecuted = false
        val house = HouseTestFixture.EXAMPLE
        val name = "${house.id.value}. ${house.name.substringAfter("House ")}"

        setThemedContent {
            HouseCardItem(
                house = house,
                onClick = { clickExecuted = true }
            )
        }

        // When
        val view = onNodeWithTag(testTagName)

        // Then
        view.assertIsDisplayed()
            .performClick()
            .assertTextEquals(name)

        clickExecuted mustEqual true
    }

    @Test
    fun should_match_design_specification() = runComposeUiTest {
        // GIVEN
        val house = HouseTestFixture.EXAMPLE

        setThemedContent {
            HouseCardItem(
                house = house,
                onClick = { }
            )
        }

        // WHEN
        val view = onNodeWithTag(testTagName)

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("feature", testTagName)
    }

    private companion object {
        const val testTagName = "HouseCardItem"
    }
}
