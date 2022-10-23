/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.di

import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import kotlin.test.Test
import kotlin.test.assertNotSame
import kotlin.test.assertSame

class InjectorInternalTest {

    private val testSubject = Injector.Internal

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType DiContract.Internal::class
    }

    @Test
    fun `SHOULD implement domain contract`() {
        testSubject.domain isOfType DiContract.Internal.Domain::class
    }

    @Test
    fun `SHOULD return only one instance for domain`() {
        assertSame(testSubject.domain, testSubject.domain)
    }

    @Test
    fun `SHOULD return multiple distinct member instances for domain`() {
        assertNotSame(
            illegal = testSubject.domain.servicePagination,
            actual = testSubject.domain.servicePagination,
        )
    }

    @Test
    fun `SHOULD implement data contract`() {
        testSubject.data isOfType DiContract.Internal.Data::class
    }

    @Test
    fun `SHOULD return only one instance for data`() {
        assertSame(testSubject.data, testSubject.data)
    }

    @Test
    fun `SHOULD return only one member instance for data`() {
        assertSame(
            actual = testSubject.data.networkIceAndFireApi,
            expected = testSubject.data.networkIceAndFireApi
        )
        assertSame(
            actual = testSubject.data.networkHttpClient,
            expected = testSubject.data.networkHttpClient
        )
    }

    @Test
    fun `SHOULD return multiple distinct instances for data`() {
        assertNotSame(
            illegal = testSubject.data.repositoryHouses,
            actual = testSubject.data.repositoryHouses,
        )
        assertNotSame(
            illegal = testSubject.data.dataSourceHousesRemote,
            actual = testSubject.data.dataSourceHousesRemote,
        )
        assertNotSame(
            illegal = testSubject.data.networkHttpsUrlConnectionFactory,
            actual = testSubject.data.networkHttpsUrlConnectionFactory,
        )
        assertNotSame(
            illegal = testSubject.data.networkJsonParser,
            actual = testSubject.data.networkJsonParser,
        )
        assertNotSame(
            illegal = testSubject.data.mapperHouses,
            actual = testSubject.data.mapperHouses,
        )
        assertNotSame(
            illegal = testSubject.data.mapperIdentifier,
            actual = testSubject.data.mapperIdentifier,
        )
    }
}
