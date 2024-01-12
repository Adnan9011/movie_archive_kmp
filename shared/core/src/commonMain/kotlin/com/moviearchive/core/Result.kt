package com.moviearchive.core

/*
 V = Value
 E = Error
 */
sealed class Result<out V, out E> {

    object Loading : Result<Nothing, Nothing>()
    data class Success<out V : Any?>(val value: V) : Result<V, Nothing>()
    data class Failure<out E : Any?>(val error: E) : Result<Nothing, E>()
}

inline fun <V, reified E : Any?> Result<V, E>.fold(
    loading: (Unit) -> Unit,
    success: (V) -> Unit,
    failure: (E) -> Unit
) =
    when (this) {
        is Result.Loading -> loading(Unit)
        is Result.Success -> success(value)
        is Result.Failure -> failure(error)
    }

inline fun <V, U, reified E : Any?> Result<V, E>.map(transform: (V) -> U): Result<U, E> {

    return when (this) {
        Result.Loading -> Result.Loading
        is Result.Success -> Result.Success(transform(value))
        is Result.Failure -> Result.Failure(error)
    }
}

