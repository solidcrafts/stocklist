package io.solidcrafts.stocklist.domain

sealed class Data<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Data<T>(data)
    class Error<T>(data: T? = null, message: String?): Data<T>(data)
    class Loading<T>(val isLoading: Boolean = true): Data<T>()
}
