package com.alexyuzefovich.pillbox.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alexyuzefovich.pillbox.R

private val ptSansNarrowBold: Font = Font(R.font.pt_sans_narrow_bold, FontWeight.Bold)
private val robotoCondensedRegular: Font = Font(R.font.roboto_condensed_regular, FontWeight.Normal)
private val robotoCondensedItalic: Font = Font(R.font.roboto_condensed_italic, FontWeight.Normal, FontStyle.Italic)

private val pillBoxFontFamily: FontFamily = FontFamily(ptSansNarrowBold, robotoCondensedRegular, robotoCondensedItalic)

// Set of Material typography styles to start with
val Typography = Typography(
    h5 = TextStyle(
        fontFamily = pillBoxFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    h6 = TextStyle(
        fontFamily = pillBoxFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        fontFamily = pillBoxFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = pillBoxFontFamily,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        fontSize = 14.sp
    )
)