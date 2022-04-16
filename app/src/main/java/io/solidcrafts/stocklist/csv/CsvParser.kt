package io.solidcrafts.stocklist.csv

import java.io.InputStream

interface CsvParser<T> {
    suspend fun parse(inputStream: InputStream): List<T>
}