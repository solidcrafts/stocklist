package io.solidcrafts.stocklist.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ListingEntity::class], version = 1)
abstract class ListingsDatabase : RoomDatabase() {
    abstract val dao: ListingsDao
}