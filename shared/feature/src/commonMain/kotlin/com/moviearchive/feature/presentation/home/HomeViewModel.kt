package com.moviearchive.feature.presentation.home

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.domain.model.toWeekTop
import com.moviearchive.domain.usecase.celebrity.GetFavoriteCelebritiesUseCase
import com.moviearchive.domain.usecase.celebrity.GetPopularCelebritiesUseCase
import com.moviearchive.domain.usecase.movie.GetFavoriteMoviesUseCase
import com.moviearchive.domain.usecase.weekTopTen.GetWeekTopTenMoviesUseCase
import com.moviearchive.feature.model.CelebrityUiModel
import com.moviearchive.feature.model.WeekTopUiModel
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
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getWeekTopTenMovies: GetWeekTopTenMoviesUseCase,
    private val getPopularCelebrities: GetPopularCelebritiesUseCase,
    private val getFavoriteCelebrities: GetFavoriteCelebritiesUseCase,
    private val getFavoriteMovies: GetFavoriteMoviesUseCase,
) : ViewModel() {

    private val _uiWeekTopTen =
        MutableStateFlow<Result<PersistentList<WeekTopUiModel>, Error>>(Result.Loading)
    val uiWeekTopTen = _uiWeekTopTen.asStateFlow()

    private val _uiPopularCelebrities =
        MutableStateFlow<Result<PersistentList<CelebrityUiModel>, Error>>(Result.Loading)
    val uiPopularCelebrities = _uiPopularCelebrities.asStateFlow()

    fun getWeekTopTen() {
        viewModelScope.launch {
            getWeekTopTenMovies()
                .flowOn(Dispatchers.IO)
                .catch { throwable ->
                    _uiWeekTopTen.value =
                        Result.Failure(
                            Error(
                                message = throwable.message ?: "",
                                throwable = throwable
                            )
                        )
                }
                .collect { result ->
                    _uiWeekTopTen.value =
                        result.map { list ->
                            list.map {
                                it.toUi()
                            }.toPersistentList()
                        }
                }
        }
    }

    private fun getFavoriteWeekTopTen() {
        viewModelScope.launch {
            getFavoriteMovies()
                .flowOn(Dispatchers.IO)
                .catch { throwable ->
                    _uiWeekTopTen.value =
                        Result.Failure(
                            Error(
                                message = throwable.message ?: "",
                                throwable = throwable
                            )
                        )
                }
                .collect { result ->
                    _uiWeekTopTen.value =
                        result.map { list ->
                            list.map {
                                it.toWeekTop().toUi()
                            }.toPersistentList()
                        }
                }
        }
    }

    fun getCelebrities() {
        viewModelScope.launch {
            getPopularCelebrities()
                .flowOn(Dispatchers.IO)
                .catch { throwable ->
                    _uiPopularCelebrities.value =
                        Result.Failure(
                            Error(
                                message = throwable.message ?: "",
                                throwable = throwable
                            )
                        )
                }
                .collect { result ->
                    _uiPopularCelebrities.value =
                        result.map { paging ->
                            paging.list.map { it.toUi() }.toPersistentList()
                        }
                }
        }
    }

    private fun getFavoriteCelebrity() {
        viewModelScope.launch {
            getFavoriteCelebrities()
                .flowOn(Dispatchers.IO)
                .catch { throwable ->
                    _uiPopularCelebrities.value =
                        Result.Failure(
                            Error(
                                message = throwable.message ?: "",
                                throwable = throwable
                            )
                        )
                }
                .collect { result ->
                    _uiPopularCelebrities.value =
                        result.map { list ->
                            list.map { it.toUi() }.toPersistentList()
                        }
                }
        }
    }

    fun getData() {
        getWeekTopTen()
        getCelebrities()
    }

    fun getFavorites() {
        getFavoriteWeekTopTen()
        getFavoriteCelebrity()
    }
}