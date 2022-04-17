package io.solidcrafts.stocklist.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.solidcrafts.stocklist.csv.CsvParser
import io.solidcrafts.stocklist.csv.ListingsCsvParser
import io.solidcrafts.stocklist.remote.ListingRemote
import io.solidcrafts.stocklist.repository.ListingsRepository
import io.solidcrafts.stocklist.repository.ListingsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCsvListingsParser(
        listingsCsvParser: ListingsCsvParser
    ): CsvParser<ListingRemote>

    @Binds
    @Singleton
    abstract fun bindListingsRepository(
        listingsRepositoryImpl: ListingsRepositoryImpl
    ): ListingsRepository
}