/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.test

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> runTestWithStateFlowCollector(
    collectedStateFlows: MutableList<T>,
    stateFlow: StateFlow<T>,
    executeTest: TestScope.() -> Unit
) = runTest {
    var collectJob: Job? = null
    try {
        collectJob = launch(UnconfinedTestDispatcher()) {
            stateFlow.collect { collectedStateFlows.add(it) }
        }
        executeTest()
    } finally {
        collectJob?.cancel()
    }
}
