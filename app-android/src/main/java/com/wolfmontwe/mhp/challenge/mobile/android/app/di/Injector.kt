/*
 * Copyright (c) 2022. Wolf-Martell Montwé
 */

package com.wolfmontwe.mhp.challenge.mobile.android.app.di

import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.DataSource
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Mapper
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.DataContract.Network
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.HousesRemoteDataSource
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.HousesRepository
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.mapper.HousesMapper
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.mapper.IdentifierMapper
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.HttpClient
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.HttpsUrlConnectionFactory
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.IceAndFireApi
import com.wolfmontwe.mhp.challenge.mobile.android.app.data.network.JsonParser
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.Repository
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.Service
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.DomainContract.UseCase
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.GetHouseByIdUseCase
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.GetHousesPaginatedUseCase
import com.wolfmontwe.mhp.challenge.mobile.android.app.domain.PaginationService

object Injector : DiContract {

    override val domain: DiContract.Domain = Domain

    internal object Domain : DiContract.Domain {
        override val useCaseGetHousesPaginated: UseCase.GetHousesPaginated
            get() = GetHousesPaginatedUseCase(
                repository = Internal.Data.repositoryHouses,
                pagination = Internal.Domain.servicePagination,
            )
        override val useCaseGetHouseById: UseCase.GetHouseById
            get() = GetHouseByIdUseCase(
                repository = Internal.Data.repositoryHouses,
            )
    }

    internal object Internal : DiContract.Internal {

        internal val domain: Domain = Domain
        internal val data: Data = Data

        internal object Domain : DiContract.Internal.Domain {
            override val servicePagination: Service.Pagination
                get() = PaginationService()
        }

        internal object Data : DiContract.Internal.Data {
            override val repositoryHouses: Repository.Houses
                get() = HousesRepository(
                    remote = dataSourceHousesRemote,
                )

            override val dataSourceHousesRemote: DataSource.Houses.Remote
                get() = HousesRemoteDataSource(
                    api = networkIceAndFireApi,
                    mapper = mapperHouses,
                )

            override val networkIceAndFireApi: Network.IceAndFireApi by lazy {
                IceAndFireApi(
                    httpClient = networkHttpClient,
                    jsonParser = networkJsonParser,
                )
            }

            override val networkHttpClient: Network.HttpClient by lazy {
                HttpClient(
                    httpsUrlConnectionFactory = networkHttpsUrlConnectionFactory
                )
            }

            override val networkHttpsUrlConnectionFactory: Network.HttpsUrlConnectionFactory
                get() = HttpsUrlConnectionFactory()

            override val networkJsonParser: Network.JsonParser
                get() = JsonParser()

            override val mapperHouses: Mapper.Houses
                get() = HousesMapper(idMapper = mapperIdentifier)

            override val mapperIdentifier: Mapper.Id
                get() = IdentifierMapper()
        }
    }
}
