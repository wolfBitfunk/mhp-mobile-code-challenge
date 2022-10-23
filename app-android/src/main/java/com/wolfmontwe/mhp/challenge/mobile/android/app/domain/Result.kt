/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Failure
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Success

sealed interface Result<out R> {
    data class Success<out T>(val data: T) : Result<T>
    data class Failure(val exception: Exception) : Result<Nothing>

    companion object {
        fun <T> success(data: T) = Success(data)
        fun failure(exception: Exception) = Failure(exception)
    }
}

fun <T> Result<T>.isSuccess() = this is Success
fun <T> Result<T>.isFailure() = this is Failure

fun <IN, OUT> Result<IN>.map(
    success: (Success<IN>) -> OUT,
    failure: (Failure) -> OUT
): OUT {
    return when (this) {
        is Success -> success(this)
        is Failure -> failure(this)
    }
}
