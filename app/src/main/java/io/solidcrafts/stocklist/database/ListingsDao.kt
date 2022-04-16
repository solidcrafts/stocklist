package io.solidcrafts.stocklist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface ListingsDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertListings(list: List<ListingEntity>)

    @Query("DELETE FROM listingentity")
    suspend fun clearAll()

    @Query("""
        SELECT * FROM listingentity
        WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%'
        OR UPPER(:query) == symbol
        """)
    suspend fun getListings(query: String = ""): List<ListingEntity>
}