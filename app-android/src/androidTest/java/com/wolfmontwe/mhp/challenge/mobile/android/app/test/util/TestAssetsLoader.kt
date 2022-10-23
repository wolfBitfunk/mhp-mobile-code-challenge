/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.test.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.test.platform.app.InstrumentationRegistry
import java.io.FileNotFoundException
import java.io.InputStream

fun loadBitmapAsset(path: String): Bitmap? = loadAsset(path) { inputStream ->
    inputStream.use { BitmapFactory.decodeStream(it) }
}

fun <T> loadAsset(path: String, mapping: (InputStream) -> T): T {
    val assets = InstrumentationRegistry.getInstrumentation().context.assets
    return try {
        val file = assets.open(path)
        mapping(file)
    } catch (exception: Throwable) {
        throw FileNotFoundException("File not found: $path")
    }
}
