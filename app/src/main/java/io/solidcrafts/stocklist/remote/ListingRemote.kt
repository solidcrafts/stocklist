package io.solidcrafts.stocklist.remote

data class ListingRemote(
    val symbol: String,
    val name: String,
    val exchange: String,
    val ipoDate: String)
