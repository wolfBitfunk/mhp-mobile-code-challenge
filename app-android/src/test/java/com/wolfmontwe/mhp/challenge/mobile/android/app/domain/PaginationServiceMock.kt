/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.Service

class PaginationServiceMock(
    private val nextPage: Int = 0,
    private val pageSize: Int = PaginationService.DEFAULT_PAGE_SIZE,
) : Service.Pagination {

    var recordedNextPage: Boolean = false
        private set

    var recordedPageSize: Boolean = false
        private set

    var recordedReset: Boolean = false
        private set

    override fun nextPage(): Int {
        recordedNextPage = true
        return nextPage
    }

    override fun pageSize(): Int {
        recordedPageSize = true
        return pageSize
    }

    override fun reset() {
        recordedReset = true
    }
}
