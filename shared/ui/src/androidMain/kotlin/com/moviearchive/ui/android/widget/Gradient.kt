package com.moviearchive.ui.android.widget

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.moviearchive.ui.widget.VerticalGradiant

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