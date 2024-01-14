package com.moviearchive.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun VerticalGradiant(
    modifier: Modifier,
    listColors: List<Color>,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listColors
                )
            ),
        content = content
    )
}

@Preview
@Composable
private fun VerticalGradiantPreview() {
    VerticalGradiant(
        modifier = Modifier,
        listColors = listOf(Color.White, Color.DarkGray, Color.Black)
    ) {
        Text(text = "Dummy Text", color = Color.White)
    }
}