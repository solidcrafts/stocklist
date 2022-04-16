package io.solidcrafts.stocklist.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListingEntity(
    @PrimaryKey val symbol: String,
    val name: String,
    val exchange: String,
    val ipoDate: String
)
