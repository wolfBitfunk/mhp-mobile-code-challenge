/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.Repository.House

interface DomainContract {

    interface Entity {
        val id: String
    }

    interface Repository {
        interface House {
            suspend fun getHouses(page: Int, pageSize: Int): Result<List<House>>
        }
    }

    interface Service {
        interface Pagination {
            fun nextPage(): Int
            fun pageSize(): Int
            fun reset()
        }
    }
}
