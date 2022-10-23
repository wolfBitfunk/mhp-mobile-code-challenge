/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.feature.house.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.atom.PrimaryTextButton

@Composable
fun HouseListView(openDetailView: (Int) -> Unit) {
    Column {
        Text(text = "HouseListView")
        PrimaryTextButton(text = "Navigate to detail", onClick = { openDetailView(123) })
    }
}
