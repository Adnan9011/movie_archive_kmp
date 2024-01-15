package com.moviearchive.ui.android.widget.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moviearchive.ui.widget.shimmer.ShimmerCircle
import com.moviearchive.ui.widget.shimmer.ShimmerRectangle
import com.moviearchive.ui.widget.shimmer.ShimmerSquare

@Preview
@Composable
private fun ShimmerCirclePreview() = ShimmerCircle(
    isEnableShimmer = true,
    size = 100.dp,
    padding = 10.dp
)

@Preview
@Composable
private fun ShimmerSquarePreview() = ShimmerSquare(
    isEnableShimmer = true,
    size = 100.dp,
    round = 25.dp,
    padding = 10.dp
)

@Preview
@Composable
private fun ShimmerRectanglePreview() = ShimmerRectangle(
    isEnableShimmer = true,
    width = 200.dp,
    height = 100.dp,
    round = 25.dp,
    padding = 10.dp
)
