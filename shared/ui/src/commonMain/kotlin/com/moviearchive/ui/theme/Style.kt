package com.moviearchive.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val HomeTextTitleStyle =
    TextStyle(
        fontSize = 20.sp,
        color = Color.Black,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold
    )

val MovieItemTitleStyle =
    TextStyle(
        fontSize = 14.sp,
        color = Color.White,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold
    )

val MovieDetailTitleStyle =
    TextStyle(
        fontSize = 14.sp,
        color = Color.Black,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Justify,
        lineHeight = 20.sp
    )

val MovieDetailTextStyle =
    TextStyle(
        fontSize = 12.sp,
        color = Color.Black,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Justify,
        lineHeight = 20.sp
    )

val MovieDetailItemTextStyle =
    TextStyle(
        fontSize = 12.sp,
        color = Color.Black,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold
    )

val EmptyTextStyle =
    TextStyle(
        fontSize = 16.sp,
        color = Color.DarkGray,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Justify,
        lineHeight = 20.sp
    )