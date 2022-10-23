/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.network

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Failure
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Success
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import javax.net.ssl.HttpsURLConnection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import java.io.IOException
import java.net.URL
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class HttpClientTest {

    private val testConnectionFactory = TestHttpsUrlConnectionFactory()
    private val testSubject = HttpClient(testConnectionFactory)

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType Network.HttpClient::class
    }

    @Test
    fun `SHOULD use HttpsUrlConnectionFactory`() = runTest {
        // GIVEN
        val url = URL("https://example.com")
        testConnectionFactory.responseCode = 200
        testConnectionFactory.payload = "success"

        // WHEN
        testSubject.get(url)

        // THEN
        testConnectionFactory.connection?.url mustEqual url
        testConnectionFactory.connection?.responseCode mustEqual 200
        testConnectionFactory.payload mustEqual "success"
    }

    @Test
    fun `SHOULD disconnect urlConnection WITH success`() = runTest {
        // GIVEN
        val url = URL("https://example.com")
        testConnectionFactory.responseCode = 200
        testConnectionFactory.payload = "success"

        // WHEN
        val result = testSubject.get(url)

        // THEN
        result isOfType Success::class
        testConnectionFactory.connection?.recordedDisconnect mustEqual true
    }

    @Test
    fun `SHOULD disconnect urlConnection WITH failure`() = runTest {
        // GIVEN
        val url = URL("https://example.com")
        testConnectionFactory.responseCode = 500

        // WHEN
        val result = testSubject.get(url)

        // THEN
        result isOfType Failure::class
        testConnectionFactory.connection?.recordedDisconnect mustEqual true
    }

    @Test
    fun `SHOULD return success and payload FOR valid response`() = runTest {
        // GIVEN
        val url = URL("https://example.com")
        testConnectionFactory.responseCode = 200
        testConnectionFactory.payload = "success"

        // WHEN
        val result = testSubject.get(url)

        // THEN
        result isOfType Success::class
        result as Success
        result.data mustEqual "success"
    }

    @Test
    fun `SHOULD return failure FOR invalid response`() = runTest {
        // GIVEN
        val url = URL("https://example.com")
        testConnectionFactory.responseCode = 500

        // WHEN
        val result = testSubject.get(url)

        // THEN
        result isOfType Failure::class
        result as Failure
        result.exception isOfType IOException::class
        result.exception.message mustEqual "Invalid response: 500 for $url"
    }

    private class TestHttpsUrlConnectionFactory() : Network.HttpsUrlConnectionFactory {
        var responseCode: Int = 200
        var payload: String = "empty"

        var connection: HttpsUrlConnectionMock? = null

        override fun createGetRequest(url: URL): HttpsURLConnection {
            return HttpsUrlConnectionMock(url, responseCode, payload).also {
                connection = it
            }
        }
    }
}
