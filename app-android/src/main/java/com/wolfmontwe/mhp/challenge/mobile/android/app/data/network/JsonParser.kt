/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.network

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.BookResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.CharacterResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response.HouseResponse
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.Result
import org.json.JSONArray
import org.json.JSONObject

class JsonParser : Network.JsonParser {

    override fun parseHouses(json: String): Result<List<HouseResponse>> =
        parseArray(json) { mapToHouses(it) }

    override fun parseHouse(json: String): Result<HouseResponse> =
        parseObject(json) { mapToHouse(it) }

    override fun parseCharacters(json: String): Result<List<CharacterResponse>> =
        parseArray(json) { mapToCharacters(it) }

    override fun parseCharacter(json: String): Result<CharacterResponse> =
        parseObject(json) { mapToCharacter(it) }

    override fun parseBooks(json: String): Result<List<BookResponse>> =
        parseArray(json) { mapToBooks(it) }

    override fun parseBook(json: String): Result<BookResponse> =
        parseObject(json) { mapToBook(it) }

    private fun <T> parseArray(json: String, mapper: (JSONArray) -> List<T>): Result<List<T>> {
        return try {
            val jsonArray = JSONArray(json)
            Result.success(mapper(jsonArray))
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    private fun <T> parseObject(json: String, mapper: (JSONObject) -> T): Result<T> {
        return try {
            val jsonObject = JSONObject(json)
            Result.success(mapper(jsonObject))
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    private fun mapToHouse(jsonObject: JSONObject): HouseResponse = HouseResponse(
        url = jsonObject.getString("url"),
        name = jsonObject.getString("name"),
        region = jsonObject.getString("region"),
        coatOfArms = jsonObject.getString("coatOfArms"),
        words = jsonObject.getString("words"),
        titles = mapToStringList(jsonObject.getJSONArray("titles")),
        seats = mapToStringList(jsonObject.getJSONArray("seats")),
        currentLord = jsonObject.getString("currentLord"),
        heir = jsonObject.getString("heir"),
        overlord = jsonObject.getString("overlord"),
        founded = jsonObject.getString("founded"),
        founder = jsonObject.getString("founder"),
        diedOut = jsonObject.getString("diedOut"),
        ancestralWeapons = mapToStringList(jsonObject.getJSONArray("ancestralWeapons")),
        cadetBranches = mapToStringList(jsonObject.getJSONArray("cadetBranches")),
        swornMembers = mapToStringList(jsonObject.getJSONArray("swornMembers")),
    )

    private fun mapToHouses(jsonArray: JSONArray): List<HouseResponse> =
        mapToEntityList(jsonArray) { mapToHouse(it) }

    private fun mapToCharacter(jsonObject: JSONObject): CharacterResponse = CharacterResponse(
        url = jsonObject.getString("url"),
        name = jsonObject.getString("name"),
        gender = jsonObject.getString("gender"),
        culture = jsonObject.getString("culture"),
        born = jsonObject.getString("born"),
        died = jsonObject.getString("died"),
        titles = mapToStringList(jsonObject.getJSONArray("titles")),
        aliases = mapToStringList(jsonObject.getJSONArray("aliases")),
        father = jsonObject.getString("father"),
        mother = jsonObject.getString("mother"),
        spouse = jsonObject.getString("spouse"),
        allegiances = mapToStringList(jsonObject.getJSONArray("allegiances")),
        books = mapToStringList(jsonObject.getJSONArray("books")),
        povBooks = mapToStringList(jsonObject.getJSONArray("povBooks")),
        tvSeries = mapToStringList(jsonObject.getJSONArray("tvSeries")),
        playedBy = mapToStringList(jsonObject.getJSONArray("playedBy")),
    )

    private fun mapToCharacters(jsonArray: JSONArray): List<CharacterResponse> =
        mapToEntityList(jsonArray) { mapToCharacter(it) }

    private fun mapToBook(jsonObject: JSONObject): BookResponse = TODO("Not yet implemented")

    private fun mapToBooks(jsonArray: JSONArray): List<BookResponse> =
        mapToEntityList(jsonArray) { mapToBook(it) }

    private fun <T> mapToEntityList(jsonArray: JSONArray, mapper: (JSONObject) -> T): List<T> {
        val list = mutableListOf<T>()
        for (i in 0 until jsonArray.length()) {
            val entity = mapper(jsonArray.getJSONObject(i))
            list.add(entity)
        }
        return list
    }

    private fun mapToStringList(jsonArray: JSONArray): List<String> {
        val list = mutableListOf<String>()
        for (i in 0 until jsonArray.length()) {
            list.add(jsonArray.getString(i))
        }
        return list
    }
}
