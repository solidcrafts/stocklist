package io.solidcrafts.stocklist.mappers

import io.solidcrafts.stocklist.database.ListingEntity
import io.solidcrafts.stocklist.domain.Listing
import io.solidcrafts.stocklist.remote.ListingRemote

fun List<ListingEntity>.toDomainListings(): List<Listing> {
    return map {
        Listing(
            name = it.name,
            symbol = it.symbol,
            exchange = it.exchange,
            ipoDate = it.ipoDate
        )
    }
}

fun List<ListingRemote>.toDatabaseListings(): List<ListingEntity> {
    return map {
        ListingEntity(
            name = it.name,
            symbol = it.symbol,
            exchange = it.exchange,
            ipoDate = it.ipoDate
        )
    }
}