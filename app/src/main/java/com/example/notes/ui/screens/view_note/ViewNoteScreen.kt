package com.example.notes.ui.screens.view_note

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.NoteViewModel
import com.example.notes.models.NoteEvent
import com.example.notes.models.TextFieldState
import com.example.notes.models.entities.Note
import com.example.notes.ui.components.general.DeleteNoteDialog
import com.example.notes.ui.components.general.FabDelete
import com.example.notes.ui.components.general.FabSave
import com.example.notes.ui.components.preview.PreviewColumn
import com.example.notes.ui.screens.new_note.EditNoteState
import com.example.notes.ui.screens.new_note.screen_components.NewNoteContent
import com.example.notes.ui.screens.new_note.screen_components.NoteTitleTopBar
import com.example.notes.ui.theme.color100
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private const val viewNoteTag = "viewNoteTag"
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ViewNoteScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel,
) {
    viewModel.selectedNote?.let {note ->
        val keyboard = LocalSoftwareKeyboardController.current
        val noteTitleTextFieldState by remember { mutableStateOf(
            TextFieldState(
                keyboard = keyboard,
                initText = note.note_title
            )
        ) }
        val noteContentTextFieldState by remember { mutableStateOf(
            TextFieldState(
                keyboard = keyboard,
                initText = note.note_details
            )
        ) }

        var showDeleteDialog by remember { mutableStateOf(false) }
        if (showDeleteDialog) {
            DeleteNoteDialog(
                onDeleteNote = {
                    showDeleteDialog = false
                    viewModel.onEvent(NoteEvent.OnBackPressed)
                    viewModel.onEvent(NoteEvent.OnDeleteNote(note))
                },
                onDismiss = {
                    showDeleteDialog = false
                }
            )
        }

        val editNoteState by remember {
            mutableStateOf(EditNoteState(
                titleTextFieldState = noteTitleTextFieldState,
                contentTextFieldState = noteContentTextFieldState,
            ))
        }


        Scaffold(
            modifier = modifier
                .fillMaxSize(),
            floatingActionButton = {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    FabSave{
                        viewModel.onEvent(NoteEvent.SaveNote(
                            // `editNoteState` initializes the note with a null id
                            // to update, we need to pass the id
                            editNoteState.note.copy(
                                id = note.id
                            )
                        ))
                    }
                    FabDelete {
                        showDeleteDialog = true
                    }
                }
            },
            topBar = {
                NoteTitleTopBar(
                    onEvent = viewModel::onEvent,
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

}

@Preview
@Composable
fun NoteDetailsScreenPreview() {
    val viewModel: NoteViewModel = viewModel(
        factory = NoteViewModel.Factory
    )
    val note = Note(
        note_title = "God did",
        note_details = "Lord, forgive me for what the stove did\n" +
                "No one touched a billi till hov did"
    )

    viewModel.selectNote(note)

    PreviewColumn {
        ViewNoteScreen(
            viewModel = viewModel
        )
    }
}