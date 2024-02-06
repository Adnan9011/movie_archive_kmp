package com.moviearchive.feature.presentation.home

import com.moviearchive.core.Error
import com.moviearchive.core.Result
import com.moviearchive.core.map
import com.moviearchive.domain.model.toWeekTop
import com.moviearchive.domain.usecase.celebrity.GetFavoriteCelebritiesUseCase
import com.moviearchive.domain.usecase.celebrity.GetPopularCelebritiesUseCase
import com.moviearchive.domain.usecase.home.GetFavoriteStatusUseCase
import com.moviearchive.domain.usecase.home.UpdateFavoriteStatusUseCase
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
import kotlinx.coroutines.Job
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
    private val getFavoriteStatus: GetFavoriteStatusUseCase,
    private val updateFavoriteStatus: UpdateFavoriteStatusUseCase,
) : ViewModel() {

    private var job = Job()
        get() {
            if (field.isCancelled) field = Job()
            return field
        }

    private val _uiFavoriteStatus =
        MutableStateFlow<Boolean>(false)
    val uiFavoriteStatus = _uiFavoriteStatus.asStateFlow()

    private val _uiWeekTopTen =
        MutableStateFlow<Result<PersistentList<WeekTopUiModel>, Error>>(Result.Loading)
    val uiWeekTopTen = _uiWeekTopTen.asStateFlow()

    private val _uiPopularCelebrities =
        MutableStateFlow<Result<PersistentList<CelebrityUiModel>, Error>>(Result.Loading)
    val uiPopularCelebrities = _uiPopularCelebrities.asStateFlow()

    fun getWeekTopTen() {
        viewModelScope.launch(job) {
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
        viewModelScope.launch(job) {
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
        viewModelScope.launch(job) {
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
        viewModelScope.launch(job) {
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

    fun getFavoriteStatus() {

        viewModelScope.launch {
            getFavoriteStatus.invoke()
                .flowOn(Dispatchers.IO)
                .catch {
                    _uiFavoriteStatus.value = false
                    getData()
                }
                .collect { isFavoriteEnable ->
                    _uiFavoriteStatus.value = isFavoriteEnable
                    getData()
                }
        }
    }

    fun updateFavoriteStatus(isFavorite: Boolean) {

        viewModelScope.launch {
            updateFavoriteStatus.invoke(isFavorite = isFavorite)
        }
    }

    fun getData() {
        cancelJobs()
        if (uiFavoriteStatus.value) getFavoriteData()
        else getAllData()
    }

    private fun getAllData() {
        getWeekTopTen()
        getCelebrities()
    }

    private fun getFavoriteData() {
        getFavoriteWeekTopTen()
        getFavoriteCelebrity()
    }

    private fun cancelJobs() {
        job.cancel()
    }
}