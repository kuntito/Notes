package com.example.notes.ui.components.general.text_field

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.notes.ui.theme.ts300

@Composable
fun PlaceHolderText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
) {
    Text(
        text = text,
        style = ts300.copy(
            color = color
        ),
        modifier = modifier,
    )
}