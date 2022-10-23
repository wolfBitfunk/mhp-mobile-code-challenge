/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.network

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import kotlin.test.Test

internal class IceAndFireApiTest {

    private val httpClientMock = HttpClientMock()

    private val testSubject = IceAndFireApi(httpClientMock)

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType Network.IceAndFireApi::class
    }
}
