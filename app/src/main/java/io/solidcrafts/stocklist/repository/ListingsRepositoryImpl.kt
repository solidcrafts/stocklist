package io.solidcrafts.stocklist.repository

import io.solidcrafts.stocklist.database.ListingsDatabase
import io.solidcrafts.stocklist.domain.Data
import io.solidcrafts.stocklist.domain.Listing
import io.solidcrafts.stocklist.mappers.toDomainListings
import io.solidcrafts.stocklist.remote.AlphaVantageApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListingsRepositoryImpl @Inject constructor(
    val remoteApi: AlphaVantageApi,
    val database: ListingsDatabase
) : ListingsRepository {

    private val listingsDao = database.dao

    override suspend fun getListings(
        fetchRemote: Boolean,
        query: String
    ): Flow<Data<List<Listing>>> {
        return flow {
            emit(Data.Loading(true))

            val listings = database.dao.getListings()

            if(!fetchRemote && listings.isNotEmpty()) {
                emit(Data.Success(listings.toDomainListings()))
                return@flow
            }

            val remoteListings = remoteApi.getListings()
            //listingsDao.insertListings(remoteListings)

            emit(Data.Loading(false))
        }
    }
}