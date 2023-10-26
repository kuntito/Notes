package com.example.notes.ui.screens.new_note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.models.NoteEvent
import com.example.notes.models.TextFieldState
import com.example.notes.models.entities.Note
import com.example.notes.ui.components.general.FabSave
import com.example.notes.ui.components.preview.PreviewColumn
import com.example.notes.ui.screens.new_note.screen_components.NewNoteContent
import com.example.notes.ui.screens.new_note.screen_components.NoteTitleTopBar
import com.example.notes.ui.theme.color100
import kotlinx.coroutines.flow.combine

class EditNoteState(
    val titleTextFieldState: TextFieldState,
    val contentTextFieldState: TextFieldState,
) {
    val note
        get() = Note(
            note_title = titleTextFieldState.textValue.value.text,
            note_details = contentTextFieldState.textValue.value.text,
        )
    val isNoteBlank = titleTextFieldState.textValue
        .combine(contentTextFieldState.textValue){ titleTextField, contentTextField ->
            titleTextField.text.isBlank() && contentTextField.text.isBlank()
        }
}

private const val newNoteTag = "newNoteTag"
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NewNoteScreen(
    modifier: Modifier = Modifier,
    onEvent: (NoteEvent) -> Unit,
) {
    val keyboard = LocalSoftwareKeyboardController.current
    val noteTitleTextFieldState by remember { mutableStateOf(TextFieldState(keyboard)) }
    val noteContentTextFieldState by remember { mutableStateOf(TextFieldState(keyboard)) }

    val editNoteState by remember {
        mutableStateOf(EditNoteState(
            titleTextFieldState = noteTitleTextFieldState,
            contentTextFieldState = noteContentTextFieldState,
        ))
    }

    val isNoteBlank by editNoteState.isNoteBlank.collectAsState(initial = true)


    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        floatingActionButton = {
            if (!isNoteBlank) {
                FabSave(
                    modifier = Modifier.padding(8.dp)
                ) {
                    val newNote = editNoteState.note
                    onEvent(NoteEvent.SaveNote(newNote))
                }
            }
        },
        topBar = {
            NoteTitleTopBar(
                onEvent = onEvent,
                textFieldState = noteTitleTextFieldState,
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = color100)
        ) {
            NewNoteContent(
                textFieldState = noteContentTextFieldState,
                modifier = Modifier
                    .padding(16.dp),
            )
        }
    }
}

@Preview
@Composable
fun NewNoteScreenPreview() {
    PreviewColumn {
        NewNoteScreen(onEvent = {})
    }
}