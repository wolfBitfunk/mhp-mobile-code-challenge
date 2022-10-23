/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wolfmontwe.mhp.challenge.mobile.android.app.R
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.organism.CardItem
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.template.ScaffoldWithTopBar
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainTheme

@Composable
fun HouseListView(openDetailView: (Int) -> Unit) {
    ScaffoldWithTopBar(
        title = stringResource(id = R.string.house_list_screen_title)
    ) { innerPadding ->
        CardItem(title = "Navigate to detail", onClick = { openDetailView(123) })
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
fun CardItemPreview() {
    MainTheme {
        HouseListView({})
    }
}
