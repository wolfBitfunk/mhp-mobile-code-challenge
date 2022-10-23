/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.di

import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual
import kotlin.test.Test
import kotlin.test.assertNotSame
import kotlin.test.assertSame

class InjectorTest {

    private val testSubject = Injector

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType DiContract::class
    }

    @Test
    fun `SHOULD only expose domain`() {
        testSubject::class.members.map { it.name } mustEqual ROOT_ALLOWED_MEMBER_NAMES
    }

    @Test
    fun `SHOULD implement domain contract`() {
        testSubject.domain isOfType DiContract.Domain::class
    }

    @Test
    fun `SHOULD return only one instance for domain`() {
        assertSame(testSubject.domain, testSubject.domain)
    }

    @Test
    fun `SHOULD domain should only expose use cases`() {
        testSubject.domain::class.members.map { it.name } mustEqual DOMAIN_ALLOWED_MEMBER_NAMES
    }

    @Test
    fun `SHOULD return multiple distinct member instances for use case`() {
        assertNotSame(
            illegal = testSubject.domain.useCaseGetHousesPaginated,
            actual = testSubject.domain.useCaseGetHousesPaginated,
        )
    }

    private companion object {
        val ROOT_ALLOWED_MEMBER_NAMES = listOf(
            "domain",

            // defaults
            "equals",
            "hashCode",
            "toString"
        )

        val DOMAIN_ALLOWED_MEMBER_NAMES = listOf(
            "useCaseGetHousesPaginated",

            // defaults
            "equals",
            "hashCode",
            "toString"
        )
    }
}
