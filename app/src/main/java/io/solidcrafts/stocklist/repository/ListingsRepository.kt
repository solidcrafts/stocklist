package io.solidcrafts.stocklist.repository

import io.solidcrafts.stocklist.domain.Data
import io.solidcrafts.stocklist.domain.Listing
import kotlinx.coroutines.flow.Flow

interface ListingsRepository {
    suspend fun getListings(
        fetchRemote: Boolean = false,
        query: String = ""
    ): Flow<Data<List<Listing>>>
}