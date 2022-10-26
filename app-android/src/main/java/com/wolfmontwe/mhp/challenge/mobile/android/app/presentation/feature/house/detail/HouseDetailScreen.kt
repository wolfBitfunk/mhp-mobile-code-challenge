/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.detail

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
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainTheme

@Composable
fun HouseDetailScreen(
    houseId: String,
    viewModel: HouseDetailContract.ViewModel = viewModel<HouseDetailViewModel>(
        factory = HouseDetailViewModelFactory(houseId)
    )
) {
    val state by viewModel.state.collectAsState()

    ScaffoldWithTopBar(
        title = state.item?.name ?: stringResource(id = string.house_detail_screen_title, houseId)
    ) { innerPadding ->
        val error = state.error
        if (error == null) {
            HouseDetailContent(
                house = state.item,
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            ErrorItem(
                title = stringResource(id = string.error_item_title),
                description = error,
                modifier = Modifier.padding(innerPadding),
                onClick = {
                    viewModel.loadItem()
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
fun HouseDetailScreenPreview() {
    MainTheme {
        HouseDetailScreen("123")
    }
}
