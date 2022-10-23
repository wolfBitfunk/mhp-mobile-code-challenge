/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.network

import javax.net.ssl.HttpsURLConnection
import java.io.InputStream
import java.net.URL
import java.security.cert.Certificate

class HttpsUrlConnectionMock(
    private val newUrl: URL,
    private var newResponseCode: Int,
    private var payload: String,
) : HttpsURLConnection(null) {

    var recordedDisconnect = false
        private set

    override fun getResponseCode(): Int = newResponseCode

    private var inputSteamOverride: InputStream = payload.byteInputStream()
    override fun getInputStream(): InputStream = inputSteamOverride

    override fun getURL(): URL = newUrl

    override fun connect() {
        TODO("Not yet implemented")
    }

    override fun disconnect() {
        recordedDisconnect = true
    }

    override fun usingProxy(): Boolean = false

    override fun getCipherSuite(): String {
        TODO("Not yet implemented")
    }

    override fun getLocalCertificates(): Array<Certificate> {
        TODO("Not yet implemented")
    }

    override fun getServerCertificates(): Array<Certificate> {
        TODO("Not yet implemented")
    }
}
