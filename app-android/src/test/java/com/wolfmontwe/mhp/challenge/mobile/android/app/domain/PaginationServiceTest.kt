/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.Service
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual

import kotlin.test.Test

class PaginationServiceTest {

    val testSubject: PaginationService = PaginationService()

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType Service.Pagination::class
    }

    @Test
    fun `SHOULD use default pageSize`() {
        // GIVEN
        val defaultPageSize = 20

        // WHEN
        val result = testSubject.pageSize()

        // THEN
        result mustEqual defaultPageSize
    }

    @Test
    fun `SHOULD use custom pageSize`() {
        // GIVEN
        val customPageSize = 99

        // WHEN
        val result = PaginationService(customPageSize).pageSize()

        // THEN
        result mustEqual customPageSize
    }

    @Test
    fun `SHOULD continuously increase page number`() {
        testSubject.nextPage() mustEqual 0
        testSubject.nextPage() mustEqual 1
        testSubject.nextPage() mustEqual 2
        testSubject.nextPage() mustEqual 3
        testSubject.nextPage() mustEqual 4
    }

    @Test
    fun `SHOULD reset page number to 0`() {
        testSubject.nextPage()
        testSubject.nextPage()
        testSubject.reset()
        testSubject.nextPage() mustEqual 0
    }
}
