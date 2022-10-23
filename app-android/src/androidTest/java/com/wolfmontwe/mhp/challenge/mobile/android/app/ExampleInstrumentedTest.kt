/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        if (BuildConfig.DEBUG) {
            assertEquals(PACKAGE_NAME + PACKAGE_NAME_DEBUG_SUFFIX, appContext.packageName)
        } else {
            assertEquals(PACKAGE_NAME, appContext.packageName)
        }
    }

    private companion object {
        private const val PACKAGE_NAME = "com.wolfmontwe.mhp.challenge.mobile.android.app"
        private const val PACKAGE_NAME_DEBUG_SUFFIX = ".debug"
    }
}
