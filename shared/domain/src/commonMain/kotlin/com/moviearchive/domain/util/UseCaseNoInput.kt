package com.moviearchive.domain.util

interface UseCaseNoInput<Output> {
    suspend operator fun invoke(): Output
}