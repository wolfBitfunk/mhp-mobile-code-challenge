/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.design.atom.AppSurface
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.navigation.Navigation
import com.wolfmontwe.mhp.challenge.mobile.android.app.presentation.theme.MainTheme

@Composable
fun IceAndFireApp() {
    MainTheme {
        AppSurface {
            val controller = rememberNavController()
            Navigation(controller)
        }
    }
}
