package com.example.notes

import android.app.Application
import com.example.notes.database.NoteDatabase

class NoteApplication: Application() {
    val database: NoteDatabase by lazy {
        NoteDatabase.getDatabase(this)
    }
}