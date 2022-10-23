/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House

interface DataContract {

    interface DataSource {
        interface Houses {
            interface Remote {
                suspend fun getHouses(page: Int, pageSize: Int): Result<List<HouseResponse>>
            }
        }
    }

    interface Mapper {
        interface Houses {
            fun mapTo(from: HouseResponse): House
        }
    }
}
