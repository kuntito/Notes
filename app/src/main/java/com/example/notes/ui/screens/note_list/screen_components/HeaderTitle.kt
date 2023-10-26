package com.example.notes.ui.screens.note_list.screen_components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.notes.ui.theme.ts400

@Composable
fun HeaderTitle(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier,
        text = text,
        style = ts400,
        textAlign = TextAlign.Center,
    )
}