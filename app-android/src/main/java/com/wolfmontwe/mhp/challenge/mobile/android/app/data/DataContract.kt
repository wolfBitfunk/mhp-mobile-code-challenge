/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.BookResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.CharacterResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.House
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity.Identifier
import javax.net.ssl.HttpsURLConnection
import java.net.URL

interface DataContract {

    interface DataSource {
        interface Houses {
            interface Remote {
                suspend fun getHouses(page: Int, pageSize: Int): Result<List<House>>
            }
        }
    }

    interface Mapper {
        interface Id {
            fun mapToDomain(from: String): Result<Identifier>
        }

        interface Houses {
            fun mapToDomain(from: HouseResponse): Result<House>
            fun mapToDomain(from: List<HouseResponse>): Result<List<House>>
        }
    }

    interface Network {

        interface IceAndFireApi {
            suspend fun loadHouses(page: Int, pageSize: Int): Result<List<HouseResponse>>
            suspend fun loadHouse(id: Int): Result<HouseResponse>

            suspend fun loadCharacters(page: Int, pageSize: Int): Result<List<CharacterResponse>>
            suspend fun loadCharacter(id: Int): Result<CharacterResponse>

            suspend fun loadBooks(page: Int, pageSize: Int): Result<List<BookResponse>>
            suspend fun loadBook(id: Int): Result<BookResponse>
        }

        interface HttpClient {
            suspend fun get(url: URL): Result<String>
        }

        interface HttpsUrlConnectionFactory {
            fun createGetRequest(url: URL): HttpsURLConnection
        }

        interface JsonParser {
            fun parseHouses(json: String): Result<List<HouseResponse>>
            fun parseHouse(json: String): Result<HouseResponse>
            fun parseCharacters(json: String): Result<List<CharacterResponse>>
            fun parseCharacter(json: String): Result<CharacterResponse>
            fun parseBooks(json: String): Result<List<BookResponse>>
            fun parseBook(json: String): Result<BookResponse>
        }
    }
}
