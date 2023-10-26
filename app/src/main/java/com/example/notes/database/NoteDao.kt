package com.example.notes.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.notes.models.entities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Upsert // This updates a note if it exists else creates it
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note WHERE note_details LIKE '%' || :query || '%'")
    fun getNotesWithQuery(query: String): Flow<List<Note>>

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note ORDER BY last_edited DESC")
    fun getNotesByRecentEdit(): Flow<List<Note>>
}