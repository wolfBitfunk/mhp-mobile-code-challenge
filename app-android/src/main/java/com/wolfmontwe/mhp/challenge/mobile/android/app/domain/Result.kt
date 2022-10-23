/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain

sealed interface Result<out R> {
    data class Success<out T>(val data: T) : Result<T>
    data class Failure(val exception: Exception) : Result<Nothing>
}
