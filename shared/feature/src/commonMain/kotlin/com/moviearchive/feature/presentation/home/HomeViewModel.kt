package com.moviearchive.feature.presentation.home

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.domain.usecase.GetAndStoreAllMovieUseCase
import com.moviearchive.domain.usecase.GetMoviesOrFavoriteMoviesUseCase
import com.moviearchive.feature.model.MovieUiModel
import com.moviearchive.feature.model.toUi
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAndStoreAllMovieUseCase: GetAndStoreAllMovieUseCase,
    private val getAllFavoriteMovieUseCase: GetMoviesOrFavoriteMoviesUseCase
) : ViewModel() {

    private val _uiMovies =
        MutableStateFlow<Result<PersistentList<MovieUiModel>, Error>>(Result.Loading)
    val uiMovies = _uiMovies.asStateFlow()

    fun getMovies() {
        viewModelScope.launch {
            getAndStoreAllMovieUseCase()
                .flowOn(Dispatchers.IO)
                .catch { throwable ->
                    updateError(throwable)
                }
                .collect { result ->
                    _uiMovies.value =
                        result.map { list ->
                            list.map {
                                it.toUi()
                            }
                        }.map {
                            it.toPersistentList()
                        }
                }
        }
    }

    fun getFavoriteMovies(isFavorite: Boolean) {
        viewModelScope.launch {
            getAllFavoriteMovieUseCase(isFavorite)
                .flowOn(Dispatchers.IO)
                .catch { throwable ->
                    updateError(throwable)
                }
                .collect { result ->
                    _uiMovies.value =
                        result.map { list ->
                            list.map {
                                it.toUi()
                            }
                        }.map {
                            it.toPersistentList()
                        }
                }
        }
    }

    private fun updateLoading(isLoading: Boolean) {
        _uiMovies.update { Result.Loading }
    }

    private fun updateError(throwable: Throwable) {
        _uiMovies.update { Result.Failure(Error(throwable = throwable)) }
    }
}