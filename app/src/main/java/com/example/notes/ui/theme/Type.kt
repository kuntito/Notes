package com.example.notes.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val baseTextStyle = TextStyle(
    fontSize = 8.sp,
    fontWeight = FontWeight.Medium,
    color = Color.White,
/*
    shadow = Shadow(
        color = Color.Black.copy(alpha = 0.45f),
        offset = Offset(
            0f,
            3f
        ),
        blurRadius = 3f
    )
*/
)

val ts100 = baseTextStyle.copy(
    fontSize = 8.sp
)

val ts200 = baseTextStyle.copy(
    fontSize = 15.sp,
)
val ts300 = baseTextStyle.copy(
    fontSize = 17.sp
)

val ts350 = baseTextStyle.copy(
    fontSize = 21.sp
)

val ts400 = baseTextStyle.copy(
    fontSize = 32.sp,
    fontWeight = FontWeight.SemiBold
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)