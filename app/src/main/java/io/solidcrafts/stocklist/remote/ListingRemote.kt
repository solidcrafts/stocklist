package io.solidcrafts.stocklist.remote

import io.solidcrafts.stocklist.database.ListingEntity

data class ListingRemote(
    val symbol: String,
    val name: String,
    val exchange: String,
    val ipoDate: String)

fun ListingRemote.toDatabaseListings(): ListingEntity {
    return ListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange,
        ipoDate = ipoDate
    )
}