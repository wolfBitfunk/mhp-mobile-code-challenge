/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.network

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import javax.net.ssl.HttpsURLConnection
import java.net.URL

class HttpsUrlConnectionFactory : Network.HttpsUrlConnectionFactory {

    override fun createGetRequest(url: URL): HttpsURLConnection {
        val connection = url.openConnection() as HttpsURLConnection
        connection.requestMethod = REQUEST_METHOD_GET
        connection.setRequestProperty(REQUEST_PROPERTY_ACCEPT_KEY, REQUEST_PROPERTY_ACCEPT_VALUE)
        return connection
    }

    companion object {
        const val REQUEST_METHOD_GET = "GET"

        const val REQUEST_PROPERTY_ACCEPT_KEY = "Accept"
        const val REQUEST_PROPERTY_ACCEPT_VALUE = "application/vnd.anapioficeandfire+json; version=1"
    }
}
