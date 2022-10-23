/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.network

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import kotlin.test.Test
import kotlin.test.assertIs

internal class HttpClientTest {

    private val testSubject = HttpClient()

    @Test
    fun `SHOULD implement contract`() {
        assertIs<Network.HttpClient>(testSubject)
    }
}
