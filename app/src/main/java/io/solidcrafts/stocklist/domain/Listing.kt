package io.solidcrafts.stocklist.domain

import androidx.room.PrimaryKey

data class Listing(
    val symbol: String,
    val name: String,
    val exchange: String,
    val ipoDate: String
)