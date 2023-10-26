package com.example.notes.ui.components.note_item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.notes.ui.components.preview.PreviewColumn
import com.example.notes.ui.theme.ts350

@Composable
fun NoteTitle(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        text = text,
        style = ts350.copy(
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Start,
        modifier = modifier
            .fillMaxWidth(),
    )
}

@Preview
@Composable
fun NoteTitlePreview() {
    val title = "i thank god i never had a baby mama"
    PreviewColumn {
        NoteTitle(
            text = title,
            modifier = Modifier
                .background(Color.Yellow)
        )
    }
}