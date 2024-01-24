package com.moviearchive.feature.presentation.home.items

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.moviearchive.feature.model.CelebritiesUiModel
import kotlinx.collections.immutable.PersistentList

@Composable
fun ShowCelebrities(
    celebrities: PersistentList<CelebritiesUiModel>,
    onShowCelebrity: (celebrity: CelebritiesUiModel) -> Unit
) {
    LazyRow {
        items(
            items = celebrities,
            key = { celebrity -> celebrity.id }
        ) { celebrity ->
            CelebrityItem(
                celebrity = celebrity,
                onShowCelebrity = onShowCelebrity
            )
        }
    }
}