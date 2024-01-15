package com.moviearchive.feature.presentation.detail

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.domain.usecase.GetMovieUseCase
import com.moviearchive.domain.usecase.UpdateMovieUseCase
import com.moviearchive.feature.model.MovieUiModel
import com.moviearchive.feature.model.toDomain
import com.moviearchive.feature.model.toUi
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    val getMovieUseCase: GetMovieUseCase,
    val updateMovieUseCase: UpdateMovieUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<Result<MovieUiModel, Error>>(Result.Loading)
    val uiState = _uiState.asStateFlow()

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            getMovieUseCase(movieId)
                .flowOn(Dispatchers.IO)
                .catch { throwable ->
                    updateError(throwable)
                }
                .collect { result ->
                    _uiState.value =
                        result.map { it.toUi() }
                }
        }
    }

    fun updateMovie(movie: MovieUiModel) {
        viewModelScope.launch {
            updateMovieUseCase(movie.toDomain())
        }
    }

    private fun updateLoading(isLoading: Boolean) {
        _uiState.update { Result.Loading }
    }

    private fun updateError(throwable: Throwable) {
        _uiState.update { Result.Failure(Error(throwable = throwable)) }
    }
}