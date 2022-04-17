package io.solidcrafts.stocklist.repository

import io.solidcrafts.stocklist.csv.CsvParser
import io.solidcrafts.stocklist.database.ListingsDatabase
import io.solidcrafts.stocklist.database.toDomainListings
import io.solidcrafts.stocklist.domain.Data
import io.solidcrafts.stocklist.domain.Listing
import io.solidcrafts.stocklist.remote.AlphaVantageApi
import io.solidcrafts.stocklist.remote.ListingRemote
import io.solidcrafts.stocklist.remote.toDatabaseListings
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.Exception

@Singleton
class ListingsRepositoryImpl @Inject constructor(
    private val remoteApi: AlphaVantageApi,
    private val database: ListingsDatabase,
    private val remoteParser: CsvParser<ListingRemote>
) : ListingsRepository {

    override suspend fun getListings(
        fetchRemote: Boolean,
        query: String
    ): Flow<Data<List<Listing>>> {
        return flow {
            emit(Data.Loading(true))
            delay(3000)//temp
            val listings = database.dao.getListings(query)
            if (!fetchRemote && listings.isNotEmpty()) {
                emit(Data.Success(listings.map { it.toDomainListings() }))
                emit(Data.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = remoteApi.getListings()
                val parsedResponse = remoteParser.parse(response.byteStream())
                parsedResponse
            } catch (exception: Exception) {
                exception.printStackTrace()
                emit(Data.Error(message = exception.localizedMessage))
                emit(Data.Loading(false))
                null
            }

            remoteListings?.let { data ->
                database.dao.insertListings(data.map { it.toDatabaseListings() })
                val updatedListings = database.dao.getListings(query)
                emit(Data.Success(updatedListings.map { it.toDomainListings() }))
                emit(Data.Loading(false))
            }
        }
    }
}