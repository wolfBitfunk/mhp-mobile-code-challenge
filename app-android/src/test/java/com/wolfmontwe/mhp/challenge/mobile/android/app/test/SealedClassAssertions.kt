/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.test

import kotlin.test.assertEquals
import kotlin.reflect.KClass

fun assertSealedMemberSize(type: KClass<*>, expectedSize: Int) {
    val subclasses = type.sealedSubclasses
    assertEquals(
        expected = expectedSize,
        actual = subclasses.size,
        message = "Sealed member size changed and test needs to be adjusted accordingly"
    )
}
