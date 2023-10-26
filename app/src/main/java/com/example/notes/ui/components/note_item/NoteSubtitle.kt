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
import com.example.notes.ui.theme.color400
import com.example.notes.ui.theme.ts200

@Composable
fun NoteSubtitle(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        text = text,
        style = ts200.copy(
            color = color400,
            fontWeight = FontWeight.Normal,
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
fun NoteSubtitlePreview() {
    val subtitle = "Wale"
    PreviewColumn {
        NoteSubtitle(
            text = subtitle,
            modifier = Modifier
                .background(Color.Yellow)
        )
    }
}