package com.example.notes.ui.components.note_item

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.notes.R
import com.example.notes.models.entities.Note
import com.example.notes.ui.components.general.icons.DropdownMenu
import com.example.notes.ui.components.general.icons.DropdownOption
import com.example.notes.ui.components.preview.PreviewColumn
import com.example.notes.ui.theme.color100
import com.example.notes.ui.theme.color400

@Composable
fun NoteTitleAndSubtitle(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        NoteTitle(text = title)
        NoteSubtitle(text = subtitle)
    }
}

@Composable
fun NoteIcon(
    modifier: Modifier = Modifier
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_note),
        contentDescription = "note icon",
        modifier = modifier
            .size(32.dp),
    )
}

@Composable
fun NoteItem(
    note: Note,
    options: List<DropdownOption>,
    onOptionClick: (DropdownOption) -> Unit,
    onNoteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    // for ripple
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = color400,
        ),
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current
            ) {
                onNoteClick()
            },

    ) {
       Row(
           horizontalArrangement = Arrangement.spacedBy(16.dp),
           verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier
               .height(72.dp)
               .fillMaxSize()
               .padding(horizontal = 8.dp, vertical = 4.dp),
       ) {
            NoteIcon()
            NoteTitleAndSubtitle(
                title = note.note_title,
                subtitle = note.note_details,
                modifier = Modifier.weight(1f)
            )
           DropdownMenu(
               options = options,
               onOptionClick = onOptionClick
           )
       }
    }
}

@Preview
@Composable
fun NoteTitleAndSubtitlePreview() {
    val title = "Vacation Locations"
    val subtitle = "santorini, maldives, vegas"
    PreviewColumn {
        NoteTitleAndSubtitle(
            title = title,
            subtitle = subtitle,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun NoteItemPreview() {
    val note = Note(
        id = 1,
        note_title = "Vacation Locations",
        note_details = "santorini, maldives, vegas",
    )
    val options = listOf(
        DropdownOption.Delete,
    )
    PreviewColumn(modifier = Modifier.background(color = color100)) {
        NoteItem(
            note = note,
            options = options,
            onOptionClick = {},
            onNoteClick = {}
        )
    }
}