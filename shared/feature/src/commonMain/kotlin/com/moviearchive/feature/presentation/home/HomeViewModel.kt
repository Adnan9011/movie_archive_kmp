package com.moviearchive.feature.presentation.home

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.domain.usecase.GetPopularCelebritiesUseCase
import com.moviearchive.domain.usecase.GetWeekTopTenMoviesUseCase
import com.moviearchive.feature.model.CelebritiesUiModel
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
    private val getPopularCelebrities: GetPopularCelebritiesUseCase
) : ViewModel() {

    private val _uiWeekTopTen =
        MutableStateFlow<Result<PersistentList<WeekTopUiModel>, Error>>(Result.Loading)
    val uiWeekTopTen = _uiWeekTopTen.asStateFlow()

    private val _uiPopularCelebrities =
        MutableStateFlow<Result<PersistentList<CelebritiesUiModel>, Error>>(Result.Loading)
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
}