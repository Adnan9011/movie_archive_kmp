package com.moviearchive.feature.presentation.celebrity

import com.moviearchive.domain.usecase.celebrity.UpdatePopularCelebritiesUseCase
import com.moviearchive.feature.model.CelebrityUiModel
import com.moviearchive.feature.model.toDomain
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch

class CelebrityViewModel(
    private val updatePopularCelebritiesUseCase: UpdatePopularCelebritiesUseCase
) : ViewModel() {

    fun updateCelebrity(celebrityUiModel: CelebrityUiModel) {
        viewModelScope.launch {
            updatePopularCelebritiesUseCase(celebrityUiModel.toDomain())
        }
    }
}