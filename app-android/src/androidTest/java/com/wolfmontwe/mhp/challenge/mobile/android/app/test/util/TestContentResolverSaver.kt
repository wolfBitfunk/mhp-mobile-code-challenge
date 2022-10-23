/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.test.util

import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.test.platform.app.InstrumentationRegistry
import java.io.OutputStream

fun saveBitmapInDownloads(name: String, bitmap: Bitmap) {
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "screenshot/$name")
        put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
    }

    saveWithContentResolver(contentValues, MediaStore.Downloads.EXTERNAL_CONTENT_URI) { outputStream ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    }
}

fun saveWithContentResolver(
    contentValues: ContentValues,
    contentUri: Uri,
    mapping: (OutputStream) -> Unit
) {
    val resolver = InstrumentationRegistry.getInstrumentation().targetContext.contentResolver
    val uri: Uri? = resolver.insert(contentUri, contentValues)

    if (uri != null) {
        runCatching {
            resolver.openOutputStream(uri)?.use { output ->
                mapping(output)
            }
        }.getOrElse {
            uri.let {
                resolver.delete(uri, null, null)
            }
        }
    } else {
        throw IllegalArgumentException("ContentResolver failed to insert: $contentUri")
    }
}
