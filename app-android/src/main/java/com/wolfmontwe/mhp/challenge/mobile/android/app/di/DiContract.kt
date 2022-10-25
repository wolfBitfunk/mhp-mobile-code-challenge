/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.di

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.DataSource
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Mapper
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.Repository
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.Service
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.UseCase

interface DiContract {

    val domain: Domain

    interface Domain {
        val useCaseGetHousesPaginated: UseCase.GetHousesPaginated
        val useCaseGetHouseById: UseCase.GetHouseById
    }

    interface Internal {

        interface Domain {
            val servicePagination: Service.Pagination
        }

        interface Data {
            val repositoryHouses: Repository.Houses

            val dataSourceHousesRemote: DataSource.Houses.Remote

            val networkIceAndFireApi: Network.IceAndFireApi
            val networkHttpClient: Network.HttpClient
            val networkHttpsUrlConnectionFactory: Network.HttpsUrlConnectionFactory
            val networkJsonParser: Network.JsonParser

            val mapperHouses: Mapper.Houses
            val mapperIdentifier: Mapper.Id
        }
    }
}
