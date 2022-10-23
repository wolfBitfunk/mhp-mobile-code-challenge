/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.network

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import java.io.IOException
import java.net.URL

class HttpClientMock(
    var isResultSuccess: Boolean = true
) : Network.HttpClient {

    var answerGet: () -> String = {
        throw IllegalArgumentException("answerGet not defined")
    }

    var recordedUrl: URL? = null
        private set

    override suspend fun get(url: URL): Result<String> {
        recordedUrl = url
        return if (isResultSuccess) {
            Result.success(answerGet())
        } else {
            Result.failure(IOException("HttpClientMock error"))
        }
    }
}
