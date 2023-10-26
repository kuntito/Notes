package com.example.notes.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(
        autoGenerate = true // it is set to `false` by default
    )
    val id: Long? = null,
    val note_title: String,
    val note_details: String,
    var last_edited: Long = 0,
) {
    override fun toString(): String {
        return """{
            |id = $id
            |title = $note_title
            |content = ${note_details.take(10)}
            |}""".trimMargin()
    }

    fun doesMatchSearchQuery(query: String): Boolean {
        val stringToSearch = listOf(note_title, note_details)
        return stringToSearch.any{
            it.contains(query, ignoreCase = true)
        }
    }
}