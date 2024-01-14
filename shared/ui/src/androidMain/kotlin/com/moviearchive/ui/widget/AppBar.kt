package com.moviearchive.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
private fun AppBarHomePreview() = AppBarHome(
    title = "Home Title",
    onFavoriteClicked = {}
)

@Preview(showBackground = true)
@Composable
private fun AppBaDetailPreview() = AppBarDetail(title = "Detailed Title", onBackClicked = {})