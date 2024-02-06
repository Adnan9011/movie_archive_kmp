package com.moviearchive.ui.android.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moviearchive.ui.widget.AppBarDetail
import com.moviearchive.ui.widget.AppBarHome

@Preview(showBackground = true)
@Composable
private fun AppBarHomePreview() = AppBarHome(
    title = "Home Title",
    isLiked = true,
    onFavoriteClicked = {}
)

@Preview(showBackground = true)
@Composable
private fun AppBaDetailPreview() = AppBarDetail(title = "Detailed Title", onBackClicked = {})