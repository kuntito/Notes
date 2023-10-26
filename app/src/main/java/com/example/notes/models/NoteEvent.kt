package com.example.notes.models

import com.example.notes.models.entities.Note
import com.example.notes.ui.components.general.icons.DropdownOption

sealed interface NoteEvent {
    object CreateNote: NoteEvent
    object OnSplash: NoteEvent
    object OnBackPressed: NoteEvent
    data class SaveNote(val note: Note): NoteEvent
    data class OnDeleteNote(val note: Note): NoteEvent
    data class OnViewNote(val note: Note): NoteEvent
}