package io.solidcrafts.stocklist.csv

import com.opencsv.CSVReader
import io.solidcrafts.stocklist.remote.ListingRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListingsCsvParser @Inject constructor(

) : CsvParser<ListingRemote> {

    override suspend fun parse(inputStream: InputStream): List<ListingRemote> {
        val csvReader = CSVReader(InputStreamReader(inputStream))
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                ListingRemote(
                    symbol = line[0],
                    name = line[1],
                    exchange = line[2],
                    ipoDate = line[4]
                )
            }.also {
                csvReader.close()
            }
        }
    }
}