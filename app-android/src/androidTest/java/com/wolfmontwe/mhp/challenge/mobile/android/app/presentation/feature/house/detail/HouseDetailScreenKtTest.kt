/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.detail

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import com.wolfmontwe.mhp.challenge.mobile.android.app.R.string
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.assertScreenshotMatches
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.setThemedContent
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.fixture.HouseTestFixture
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class HouseDetailScreenKtTest {

    @Test
    fun should_show_content() = runComposeUiTest {
        // GIVEN
        val viewModel = HouseDetailViewModelMock(
            initialViewState = HouseDetailViewState(
                item = HouseTestFixture.EXAMPLE
            )
        )

        setThemedContent {
            HouseDetailScreen(
                houseId = HouseTestFixture.EXAMPLE_ID,
                viewModel = viewModel
            )
        }

        // WHEN
        onNodeWithTag("ScaffoldWithTopBar")
            .assertIsDisplayed()

        // THEN
        onNodeWithTag("HouseDetailContent")
            .assertExists()
    }

    @Test
    fun should_show_error_when_state_error() = runComposeUiTest {
        // GIVEN
        lateinit var errorTitle: String
        val errorMessage = "This is a Bug"
        val viewModel = HouseDetailViewModelMock(
            HouseDetailViewState(error = errorMessage)
        )

        setThemedContent {
            errorTitle = stringResource(id = string.error_item_title)
            HouseDetailScreen(
                houseId = HouseTestFixture.EXAMPLE_ID,
                viewModel = viewModel
            )
        }

        // WHEN
        onNodeWithTag("ScaffoldWithTopBar")
            .assertIsDisplayed()

        // THEN
        onNodeWithTag("ErrorItem")
            .assertIsDisplayed()

        onNodeWithTag("CardHeader")
            .assertHasNoClickAction()
            .assertTextEquals(errorTitle)

        onNodeWithTag("CardBody")
            .assertHasNoClickAction()
            .assertTextEquals(errorMessage)
    }

    @Test
    fun should_match_design_specification() = runComposeUiTest {
        // GIVEN
        val viewModel = HouseDetailViewModelMock(
            initialViewState = HouseDetailViewState(
                item = HouseTestFixture.EXAMPLE
            )
        )

        setThemedContent {
            HouseDetailScreen(
                houseId = HouseTestFixture.EXAMPLE_ID,
                viewModel = viewModel
            )
        }

        // WHEN
        val view = onNodeWithTag("ScaffoldWithTopBar")

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("feature", "HouseDetailScreenContent")
    }

    @Test
    fun should_match_design_specification_for_error() = runComposeUiTest {
        // GIVEN
        val errorMessage = "This is a Bug"
        val viewModel = HouseDetailViewModelMock(
            HouseDetailViewState(error = errorMessage)
        )

        setThemedContent {
            HouseDetailScreen(
                houseId = HouseTestFixture.EXAMPLE_ID,
                viewModel = viewModel
            )
        }

        // WHEN
        val view = onNodeWithTag("ScaffoldWithTopBar")

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("feature", "HouseDetailScreenError")
    }
}
