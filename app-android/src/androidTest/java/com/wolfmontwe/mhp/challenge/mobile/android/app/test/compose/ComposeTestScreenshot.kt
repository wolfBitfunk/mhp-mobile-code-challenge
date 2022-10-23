/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.test.compose

import android.graphics.Bitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.captureToImage
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.util.loadBitmapAsset
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.util.saveBitmapInDownloads

/**
 * On device screenshot comparison against an expected screenshot.
 *
 * Expected screenshots must be available `androidTest/assets`.
 *
 * Screenshot dimensions must match, otherwise test will fail. Limit the surface to a fixed size.
 *
 * If `saveAsExpected` is enabled, current screenshot will be saved on device in `downloads`
 *
 * To copy the saved images to `androidTest/assets` use the `copy_screenshot.sh` script in project root folder.
 */
fun SemanticsNodeInteraction.assertScreenshotMatches(
    folderPath: String,
    fileName: String,
    saveAsExpected: Boolean = false
) {
    val actualBitmap = captureToImage().asAndroidBitmap()
    val screenShotName = "$fileName-${actualBitmap.width}x${actualBitmap.height}.png"
    val screenShotPath = "$folderPath/$screenShotName"

    if (saveAsExpected) {
        saveBitmapInDownloads(screenShotPath, actualBitmap)
    }

    val expectedBitmap = loadBitmapAsset(screenShotPath)
    if (expectedBitmap == null) {
        throw AssertionError("expected screenshot not present in assets folder: $screenShotPath")
    } else {
        actualBitmap.compare(expectedBitmap)
    }
}

private fun Bitmap.compare(other: Bitmap) {
    assert(this.width == other.width && this.height == other.height) {
        "dimensions must be equal: ${height}x$width vs other ${other.height}x${other.width}"
    }

    for (column in 0 until height) {
        val row1 = this.readRow(column)
        val row2 = other.readRow(column)

        assert(row1.contentEquals(row2)) { "Screenshot row content is different" }
    }
}

private fun Bitmap.readRow(column: Int): IntArray {
    val row = IntArray(width)
    this.getPixels(row, 0, width, 0, column, width, 1)
    return row
}
