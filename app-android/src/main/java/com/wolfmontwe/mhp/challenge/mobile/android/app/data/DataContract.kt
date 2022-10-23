/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.BookResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.CharacterResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import javax.net.ssl.HttpsURLConnection
import java.net.URL

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

    interface Network {

        interface IceAndFireApi {
            suspend fun loadHouses(): List<HouseResponse>
            suspend fun loadHouse(id: Int): HouseResponse

            suspend fun loadCharacters(): List<CharacterResponse>
            suspend fun loadCharacter(id: Int): CharacterResponse

            suspend fun loadBooks(): List<BookResponse>
            suspend fun loadBook(id: Int): BookResponse
        }

        interface HttpClient {
            suspend fun get(url: URL): Result<String>
        }

        interface HttpsUrlConnectionFactory {
            fun createGetRequest(url: URL): HttpsURLConnection
        }
    }
}
