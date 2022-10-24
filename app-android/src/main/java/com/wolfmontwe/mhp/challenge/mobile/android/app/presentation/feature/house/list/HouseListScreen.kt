/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wolfmontwe.mhp.challenge.mobile.android.app.R.string
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.organism.ErrorItem
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.template.ScaffoldWithTopBar
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainThemeWithSurface

@Composable
fun HouseListScreen(
    openDetailView: (String) -> Unit,
    viewModel: HouseListContract.ViewModel = viewModel<HouseListViewModel>(
        factory = HouseListViewModelFactory()
    )
) {
    val state by viewModel.state.collectAsState()

    ScaffoldWithTopBar(
        title = stringResource(id = string.house_list_screen_title)
    ) { innerPadding ->
        val error = state.error
        if (error == null) {
            val items = state.items
            HouseListContent(
                innerPadding = innerPadding,
                items = items,
                isLoading = state.isLoading,
                onClickHouse = openDetailView,
                onLastListItem = {
                    viewModel.loadMoreItems()
                }
            )
        } else {
            ErrorItem(
                title = stringResource(id = string.error_item_title),
                description = error,
                modifier = Modifier.padding(innerPadding),
                onClick = {
                    viewModel.reset()
                    viewModel.loadMoreItems()
                },
            )
        }
    }
}

@Preview(
    name = "Light preview",
    showBackground = true,
)
@Preview(
    name = "Dark preview",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun HouseListScreenPreview() {
    MainThemeWithSurface {
        HouseListScreen(
            openDetailView = {}
        )
    }
}
