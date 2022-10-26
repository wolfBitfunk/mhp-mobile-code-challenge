/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainThemeDimension
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainThemeWithSurface

@Composable
fun HouseListContent(
    innerPadding: PaddingValues,
    items: List<House>,
    isLoading: Boolean,
    onClickHouse: (String) -> Unit,
    onLastListItem: () -> Unit,
) {
    LazyColumn(
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(MainThemeDimension.spacingHalf),
        modifier = Modifier.testTag("HouseListContent")
    ) {
        items(items.size) { index ->
            val item = items[index]
            if (index >= items.size - 1) {
                onLastListItem()
            }
            HouseCardItem(house = item, onClick = { onClickHouse(item.id.value) })
        }
        item {
            if (isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MainThemeDimension.spacingSingle),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
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
fun HouseListContentPreview() {
    MainThemeWithSurface {
        HouseListContent(
            innerPadding = PaddingValues(0.dp),
            items = listOf(
                House(
                    id = Identifier("123"),
                    name = "House Test"
                )
            ),
            isLoading = true,
            onClickHouse = {},
            onLastListItem = {}
        )
    }
}
