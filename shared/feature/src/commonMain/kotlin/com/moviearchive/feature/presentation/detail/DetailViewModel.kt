package com.moviearchive.feature.presentation.detail

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.domain.usecase.movie.GetMovieUseCase
import com.moviearchive.domain.usecase.weekTopTen.UpdateWeekTopTenMoviesUseCase
import com.moviearchive.feature.model.MovieUiModel
import com.moviearchive.feature.model.toUi
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class DetailViewModel(
    val getMovieUseCase: GetMovieUseCase,
    val updateTopTenMoviesUseCase: UpdateWeekTopTenMoviesUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<Result<MovieUiModel, Error>>(Result.Loading)
    val uiState = _uiState.asStateFlow()

    private val _movieTitle =
        MutableStateFlow<String>("Movie")
    val movieTitle = _movieTitle.asStateFlow()

    fun getMovie(movieId: String) {
        viewModelScope.launch {
            getMovieUseCase(movieId)
                .flowOn(Dispatchers.IO)
                .catch { throwable ->
                    _uiState.value =
                        Result.Failure(
                            Error(
                                message = throwable.message ?: "",
                                throwable = throwable
                            )
                        )
                }
                .collect { result ->

                    _uiState.value =
                        result.map {
                            it.toUi().also {
                                _movieTitle.value =
                                    it.title
                            }
                        }
                }
        }
    }
}