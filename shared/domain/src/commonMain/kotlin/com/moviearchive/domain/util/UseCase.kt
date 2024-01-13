package com.moviearchive.domain.util

interface UseCase<Input, Output> {
    suspend operator fun invoke(input: Input): Output
}