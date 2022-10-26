/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain.entity

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.Entity

data class House(
    override val id: Identifier,
    val name: String,
    val region: String,
    val coatOfArms: String,
    val words: String,
    val titles: List<String>,
    val seats: List<String>,
    val members: Int,
    val founded: String,
) : Entity
