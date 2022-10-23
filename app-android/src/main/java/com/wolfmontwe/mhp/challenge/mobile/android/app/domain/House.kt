/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.domain

import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.Entity

data class House(
    override val id: String
) : Entity
