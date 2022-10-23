/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.test

import kotlin.reflect.KClass
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertIs

inline infix fun <reified T : Any> Any.isOfType(type: KClass<T>) {
    assertIs<T>(this)
}

inline infix fun <reified T> T.mustEqual(expected: T) {
    assertEquals(
        expected = expected,
        actual = this,
    )
}

inline infix fun <reified K, reified V> Map<K, V>.mustContainKey(key: K) {
    assertContains(
        map = this,
        key = key,
    )
}
