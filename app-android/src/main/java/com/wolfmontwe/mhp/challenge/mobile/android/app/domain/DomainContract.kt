/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House

interface DomainContract {

    interface Entity {
        val id: Identifier
    }

    interface UseCase {
        interface GetHousesPaginated {
            suspend fun getHouses(): Result<List<House>>
        }
    }

    interface Repository {
        interface Houses {
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
