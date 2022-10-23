/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.mapper

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Mapper
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Failure
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result.Success
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.isOfType
import com.wolfmontwe.mhp.challenge.mobile.android.app.test.mustEqual

import kotlin.test.Test

class IdentifierMapperTest {

    private val testSubject = IdentifierMapper()

    @Test
    fun `SHOULD implement contract`() {
        testSubject isOfType Mapper.Id::class
    }

    @Test
    fun `SHOULD return failure when url malformed`() {
        testSubject.mapToDomain("example") isOfType Failure::class
        testSubject.mapToDomain("example/124") isOfType Failure::class
        testSubject.mapToDomain("example.com") isOfType Failure::class
        testSubject.mapToDomain("example.com/67") isOfType Failure::class
    }

    @Test
    fun `SHOULD map url to identifier`() {
        (testSubject.mapToDomain("https://example.com/148") as Success)
            .data mustEqual Identifier(value = "148")
        (testSubject.mapToDomain("https://example.com/path/15") as Success)
            .data mustEqual Identifier(value = "15")
        (testSubject.mapToDomain("https://example.com/path/abc/5") as Success)
            .data mustEqual Identifier(value = "5")
        (testSubject.mapToDomain("https://example.com/path/158/889") as Success)
            .data mustEqual Identifier(value = "889")
        (testSubject.mapToDomain("https://example.com/path/158/abc/9999") as Success)
            .data mustEqual Identifier(value = "9999")
    }
}
