package com.moviearchive.ui.widget

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun TryAgainButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle
) {
    Button(
        onClick = onClick,
        modifier = modifier,
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}