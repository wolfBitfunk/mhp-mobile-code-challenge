/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.network

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import java.net.URL

class HttpClientMock : Network.HttpClient {
    override suspend fun get(url: URL): Result<String> {
        TODO("Not yet implemented")
    }
}
