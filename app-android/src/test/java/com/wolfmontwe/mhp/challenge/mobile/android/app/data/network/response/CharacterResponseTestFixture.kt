/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.response

object CharacterResponseTestFixture {

    val JSON_COMPLETE_TEST_EXAMPLE = """
            {
            	"url": "testCharacterUrl",
            	"name": "testCharacterName",
            	"gender": "testCharacterGender",
            	"culture": "testCharacterCulture",
            	"born": "testCharacterBorn",
            	"died": "testCharacterDied",
            	"titles": [
            		"testCharacterTitle1"
            	],
            	"aliases": [
            		"testCharacterAlias1",
            		"testCharacterAlias2"
            	],
            	"father": "testCharacterFather",
            	"mother": "testCharacterMother",
            	"spouse": "testCharacterSpouse",
            	"allegiances": [
            		"testCharacterAllegiance1"
            	],
            	"books": [
            		"testCharacterBook1"
            	],
            	"povBooks": [
            		"testCharacterPovBook1",
            		"testCharacterPovBook2"
            	],
            	"tvSeries": [
            		"testCharacterTvSeries1",
            		"testCharacterTvSeries2"
            	],
            	"playedBy": [
            		"testCharacterPlayedBy1"
            	]
            }
        """.trimIndent()

    val JSON_LIST_OF_COMPLETE_TEST_EXAMPLES = "[$JSON_COMPLETE_TEST_EXAMPLE,$JSON_COMPLETE_TEST_EXAMPLE]"

    val COMPLETE_TEST_EXAMPLE = CharacterResponse(
        url = "testCharacterUrl",
        name = "testCharacterName",
        gender = "testCharacterGender",
        culture = "testCharacterCulture",
        born = "testCharacterBorn",
        died = "testCharacterDied",
        titles = listOf("testCharacterTitle1"),
        aliases = listOf("testCharacterAlias1", "testCharacterAlias2"),
        father = "testCharacterFather",
        mother = "testCharacterMother",
        spouse = "testCharacterSpouse",
        allegiances = listOf("testCharacterAllegiance1"),
        books = listOf("testCharacterBook1"),
        povBooks = listOf("testCharacterPovBook1", "testCharacterPovBook2"),
        tvSeries = listOf("testCharacterTvSeries1", "testCharacterTvSeries2"),
        playedBy = listOf("testCharacterPlayedBy1"),
    )

    val JSON_EXAMPLE = """
        {
            "url": "https://anapioficeandfire.com/api/characters/583",
            "name": "Jon Snow",
            "gender": "Male",
            "culture": "Northmen",
            "born": "In 283 AC",
            "died": "",
            "titles": [
                "Lord Commander of the Night's Watch"
            ],
            "aliases": [
                "Lord Snow",
                "Ned Stark's Bastard",
                "The Snow of Winterfell",
                "The Crow-Come-Over",
                "The 998th Lord Commander of the Night's Watch",
                "The Bastard of Winterfell",
                "The Black Bastard of the Wall",
                "Lord Crow"
            ],
            "father": "",
            "mother": "",
            "spouse": "",
            "allegiances": [
                "https://anapioficeandfire.com/api/houses/362"
            ],
            "books": [
                "https://anapioficeandfire.com/api/books/5"
            ],
            "povBooks": [
                "https://anapioficeandfire.com/api/books/1",
                "https://anapioficeandfire.com/api/books/2",
                "https://anapioficeandfire.com/api/books/3",
                "https://anapioficeandfire.com/api/books/8"
            ],
            "tvSeries": [
                "Season 1",
                "Season 2",
                "Season 3",
                "Season 4",
                "Season 5",
                "Season 6"
            ],
            "playedBy": [
                "Kit Harington"
            ]
        }
    """.trimIndent()

    val EXAMPLE = CharacterResponse(
        url = "https://anapioficeandfire.com/api/characters/583",
        name = "Jon Snow",
        gender = "Male",
        culture = "Northmen",
        born = "In 283 AC",
        died = "",
        titles = listOf(
            "Lord Commander of the Night's Watch"
        ),
        aliases = listOf(
            "Lord Snow",
            "Ned Stark's Bastard",
            "The Snow of Winterfell",
            "The Crow-Come-Over",
            "The 998th Lord Commander of the Night's Watch",
            "The Bastard of Winterfell",
            "The Black Bastard of the Wall",
            "Lord Crow"
        ),
        father = "",
        mother = "",
        spouse = "",
        allegiances = listOf(
            "https://anapioficeandfire.com/api/houses/362"
        ),
        books = listOf(
            "https://anapioficeandfire.com/api/books/5"
        ),
        povBooks = listOf(
            "https://anapioficeandfire.com/api/books/1",
            "https://anapioficeandfire.com/api/books/2",
            "https://anapioficeandfire.com/api/books/3",
            "https://anapioficeandfire.com/api/books/8"
        ),
        tvSeries = listOf(
            "Season 1",
            "Season 2",
            "Season 3",
            "Season 4",
            "Season 5",
            "Season 6"
        ),
        playedBy = listOf(
            "Kit Harington"
        )
    )
}
