/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

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
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.assertScreenshotMatches
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.setThemedContent
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose.setThemedScreenshotContent
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.fixture.HouseTestFixture
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class HouseListScreenKtTest {

    @Test
    fun should_show_content() = runComposeUiTest {
        // GIVEN
        val viewModel = HouseListViewModelMock(
            initialViewState = HouseListViewState(
                items = HouseTestFixture.EXAMPLE_LIST
            )
        )

        setThemedContent {
            HouseListScreen(
                openDetailView = {},
                viewModel
            )
        }

        // WHEN
        onNodeWithTag("ScaffoldWithTopBar")
            .assertIsDisplayed()

        // THEN
        onNodeWithTag("HouseListContent")
            .assertExists()
    }

    @Test
    fun should_show_error_when_state_error() = runComposeUiTest {
        // GIVEN
        lateinit var errorTitle: String
        val errorMessage = "This is a Bug"
        val viewModel = HouseListViewModelMock(
            initialViewState = HouseListViewState(error = errorMessage)
        )

        setThemedContent {
            errorTitle = stringResource(id = R.string.error_item_title)
            HouseListScreen(
                openDetailView = {},
                viewModel
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
    fun should_call_reset_and_loadMoreItems_when_clicked() = runComposeUiTest {
        // GIVEN
        val viewModel = HouseListViewModelMock(HouseListViewState(error = "errorMessage"))

        setThemedContent {
            HouseListScreen(
                openDetailView = {},
                viewModel
            )
        }

        // WHEN
        onNodeWithTag("ScaffoldWithTopBar")
            .assertIsDisplayed()

        // THEN
        onNodeWithTag("PrimaryTextButton")
            .assertIsDisplayed()
            .assertHasClickAction()
            .performClick()

        viewModel.recorededReset mustEqual true
        viewModel.recordedLoadMoreItems mustEqual true
    }

    @Test
    fun should_match_design_specification() = runComposeUiTest {
        // GIVEN
        val viewModel = HouseListViewModelMock(
            HouseListViewState(
                items = listOf(
                    House(
                        id = Identifier("123"),
                        name = "HouseName"
                    )
                )
            )
        )

        setThemedScreenshotContent {
            HouseListScreen(
                openDetailView = {},
                viewModel
            )
        }

        // WHEN
        val view = onNodeWithTag("ScaffoldWithTopBar")

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("feature", "HouseListScreenContent")
    }

    @Test
    fun should_match_design_specification_for_error() = runComposeUiTest {
        // GIVEN
        val viewModel = HouseListViewModelMock(
            HouseListViewState(error = "This is a bug!")
        )

        setThemedScreenshotContent {
            HouseListScreen(
                openDetailView = {},
                viewModel
            )
        }

        // WHEN
        val view = onNodeWithTag("ScaffoldWithTopBar")

        // THEN
        view.assertIsDisplayed()
            .assertScreenshotMatches("feature", "HouseListScreenError")
    }
}
