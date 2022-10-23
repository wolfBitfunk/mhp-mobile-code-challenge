/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.network

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustContainKey
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import java.net.URL

import kotlin.test.Test
import kotlin.test.assertFailsWith

internal class HttpsUrlConnectionFactoryTest {

    private val testSubject = HttpsUrlConnectionFactory()

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType Network.HttpsUrlConnectionFactory::class
    }

    @Test
    fun `SHOULD create a get request and configure it for JSON`() {
        // GIVEN
        val url = URL("https://example.com")

        // WHEN
        val result = testSubject.createGetRequest(url)

        // THEN
        result.requestMethod mustEqual "GET"
        result.requestProperties.size mustEqual 1
        result.requestProperties mustContainKey "Accept"
        result.requestProperties["Accept"]?.get(0) mustEqual "application/vnd.anapioficeandfire+json; version=1"
    }

    @Test
    fun `SHOULD fail for http url`() {
        // GIVEN
        val url = URL("http://example.com")

        // WHEN/THEN
        assertFailsWith(java.lang.ClassCastException::class) {
            testSubject.createGetRequest(url)
        }
    }
}
