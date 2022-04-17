package io.solidcrafts.stocklist.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.solidcrafts.stocklist.database.ListingsDatabase
import io.solidcrafts.stocklist.remote.AlphaVantageApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideAlphaVantageApi(): AlphaVantageApi {
        return Retrofit.Builder()
            .baseUrl(AlphaVantageApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideListingsDatabase(app: Application): ListingsDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            ListingsDatabase::class.java,
            "listings.db"
        ).build()
    }
}