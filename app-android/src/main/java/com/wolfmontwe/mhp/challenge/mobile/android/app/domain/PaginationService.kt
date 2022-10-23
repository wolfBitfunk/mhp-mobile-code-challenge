/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain

class PaginationService(
    private val pageSize: Int = DEFAULT_PAGE_SIZE
) : DomainContract.Service.Pagination {

    private var page: Int = 1

    override fun nextPage(): Int {
        return page++
    }

    override fun pageSize(): Int = pageSize

    override fun reset() {
        page = 1
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }
}
