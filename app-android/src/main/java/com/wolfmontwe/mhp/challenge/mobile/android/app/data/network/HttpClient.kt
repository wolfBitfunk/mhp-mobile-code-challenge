/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.network

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class HttpClient(
    private val httpsUrlConnectionFactory: Network.HttpsUrlConnectionFactory
) : Network.HttpClient {
    override suspend fun get(url: URL): Result<String> {
        val result = withContext(Dispatchers.IO) {
            val connection = httpsUrlConnectionFactory.createGetRequest(url)
            try {
                when (val responseCode = connection.responseCode) {
                    HttpURLConnection.HTTP_OK -> Result.success(readResponse(connection.inputStream))
                    else -> createResponseCodeFailure(responseCode, url)
                }
            } catch (error: Exception) {
                Result.failure(error)
            } finally {
                connection.disconnect()
            }
        }

        return result
    }

    private fun readResponse(inputStream: InputStream): String {
        return inputStream.bufferedReader().use { it.readText() }
    }

    private fun createResponseCodeFailure(responseCode: Int, url: URL): Result.Failure {
        return Result.failure(IOException("Invalid response: $responseCode for $url"))
    }
}
