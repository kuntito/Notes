package com.example.notes.ui.screens.note_list

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.NoteViewModel
import com.example.notes.models.NoteEvent
import com.example.notes.models.NoteSearchState
import com.example.notes.models.TextFieldState
import com.example.notes.models.entities.Note
import com.example.notes.ui.components.general.DeleteNoteDialog
import com.example.notes.ui.components.general.FabAdd
import com.example.notes.ui.components.general.icons.DropdownOption
import com.example.notes.ui.components.note_item.NoteItem
import com.example.notes.ui.components.preview.PreviewColumn
import com.example.notes.ui.screens.note_list.screen_components.NoteListSearchTopBar
import com.example.notes.ui.screens.note_list.screen_components.NoteListTopBarState
import com.example.notes.ui.theme.color400
import com.example.notes.ui.theme.ts300
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NoteListSearchScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel,
) {
    val nltbState by remember{ mutableStateOf(NoteListTopBarState()) }
    val keyboard = LocalSoftwareKeyboardController.current
    val searchTextFieldState by remember { mutableStateOf(TextFieldState(keyboard)) }
    val isOpenSearch by nltbState.isOpenSearch.collectAsState()


    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        floatingActionButton = {
            if(!isOpenSearch) {
                FabAdd { viewModel.onEvent(NoteEvent.CreateNote) }
            }
        },
        topBar = {
            NoteListSearchTopBar(
                noteListTopBarState = nltbState,
                textFieldState = searchTextFieldState
            )
        },
    ) { paddingValues ->
        if(isOpenSearch) {
            SearchScreen(
                paddingValues = paddingValues,
                viewModel = viewModel,
                searchText = searchTextFieldState.textValue,
            )
            BackHandler {
                nltbState.closeSearch()
            }
        } else {
            AllNotes(
                viewModel = viewModel,
                paddingValues = paddingValues,
            )
        }
    }
}

@Composable
fun AllNotes(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel,
    paddingValues: PaddingValues,
) {
    val allNotes by viewModel.allNotes.collectAsState(initial = emptyList())
    var displayTextIfNotesEmpty by remember{ mutableStateOf(false) }

    LaunchedEffect(Unit) {
        // it usually takes a few milliseconds for ROOM to retrieve data
        // the "create new note" text should not be displayed until then
        delay(500)
        displayTextIfNotesEmpty = true
    }

    if (allNotes.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (displayTextIfNotesEmpty) {
                    Text(text = "create new note", style = ts300.copy(color = color400))
                }
            }
        }
    } else {
        NotesListColumn(
            paddingValues = paddingValues,
            notes = allNotes,
            viewModel = viewModel
        )
    }
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: NoteViewModel,
    searchText: StateFlow<TextFieldValue>,
) {
    val coroutineScope = rememberCoroutineScope()

    val searchState by remember { mutableStateOf(
        NoteSearchState(
            coroutineScope = coroutineScope,
            allNotes = viewModel.allNotes,
            searchTextField = searchText,
        )
    ) }


    val notesFound by searchState.notesFound.collectAsState()
    val isSearching by searchState.isSearching.collectAsState()



    if (isSearching) {
        Box(modifier = modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        if (notesFound.isNotEmpty()) {
            NotesListColumn(
                paddingValues = paddingValues,
                notes = notesFound,
                viewModel = viewModel,
                modifier = modifier,
            )
        }
    }
}

@Composable
fun NotesListColumn(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    notes: List<Note>,
    viewModel: NoteViewModel,
) {
    var selectedNote: Note? by remember { mutableStateOf(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    if (showDeleteDialog) {
        DeleteNoteDialog(
            onDeleteNote = {
                showDeleteDialog = false
                viewModel.onEvent(NoteEvent.OnDeleteNote(selectedNote!!))
            },
            onDismiss = {
                showDeleteDialog = false
            }
        )
    }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .padding(paddingValues),
    ) {
        items(notes.size) { index ->
            val note = notes[index]
            NoteItem(
                note = note,
                options = viewModel.noteDropdownOptions,
                onOptionClick = {
                    if (it == DropdownOption.Delete) {
                        showDeleteDialog = true
                        selectedNote = note
                    }
                },
                onNoteClick = {
                    viewModel.onEvent(NoteEvent.OnViewNote(note))
                },
                modifier = Modifier
            )
        }
    }
}

@Preview
@Composable
fun NoteListScreenPreview() {
    val viewModel: NoteViewModel = viewModel(
        factory = NoteViewModel.Factory
    )
    PreviewColumn {
        NoteListSearchScreen(viewModel = viewModel)
    }
}
