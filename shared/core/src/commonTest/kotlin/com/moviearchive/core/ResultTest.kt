package com.moviearchive.core

import kotlin.test.Test
import kotlin.test.assertEquals

class ResultTest {
    @Test
    fun mapSuccess() {
        val result: Result<Int, String> = Result.Success(1)
        val mapped = result.map { it + 1 }
        assertEquals(Result.Success(2), mapped)
    }

    @Test
    fun mapFailure() {
        val error = "failure"
        val result: Result<Int, String> = Result.Failure(error)
        val mapped = result.map { it + 1 }
        assertEquals(Result.Failure(error), mapped)
    }

    @Test
    fun mapLoading() {
        val result: Result<Int, String> = Result.Loading
        val mapped = result.map { it + 1 }
        assertEquals(Result.Loading, mapped)
    }
}
