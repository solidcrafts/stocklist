package io.solidcrafts.stocklist.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.solidcrafts.stocklist.domain.Listing

@Entity
data class ListingEntity(
    @PrimaryKey val symbol: String,
    val name: String,
    val exchange: String,
    val ipoDate: String
)

fun ListingEntity.toDomainListings(): Listing {
    return Listing(
        name = name,
        symbol = symbol,
        exchange = exchange,
        ipoDate = ipoDate
    )
}
