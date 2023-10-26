package com.example.notes.models

import androidx.compose.ui.text.input.TextFieldValue
import com.example.notes.models.entities.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val noteSearchStateTag = "noteSearchStateTag"
class NoteSearchState(
    coroutineScope: CoroutineScope,
    allNotes: Flow<List<Note>>,
    searchTextField: StateFlow<TextFieldValue>,
) {
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()


    @OptIn(FlowPreview::class)
    val notesFound: StateFlow<List<Note>> = searchTextField
        .debounce(500L)// Delay execution to avoid unnecessary searches
        // for searching animation
        .onEach {
            _isSearching.update { true }
        }
        .combine(allNotes) { textField, notes ->
            if (textField.text.isBlank()) {
                emptyList()
            } else {
                notes.filter {
                    it.doesMatchSearchQuery(textField.text)
                }
            }
        }
        // for searching animation
        .onEach {
            _isSearching.update { false }
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = listOf()
        )
}