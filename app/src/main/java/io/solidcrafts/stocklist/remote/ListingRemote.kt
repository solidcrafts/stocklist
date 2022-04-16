package io.solidcrafts.stocklist.remote

import androidx.room.PrimaryKey

data class ListingRemote(
    val symbol: String,
    val name: String,
    val exchange: String,
    val ipoDate: String)
