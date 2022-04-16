package io.solidcrafts.stocklist.database

import androidx.room.Database

@Database(entities = [ListingEntity::class], version = 1)
abstract class ListingsDatabase {
    abstract val dao: ListingsDao
}