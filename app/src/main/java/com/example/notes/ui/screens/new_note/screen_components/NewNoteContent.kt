package com.example.notes.ui.screens.new_note.screen_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.models.TextFieldState
import com.example.notes.ui.components.general.text_field.NoteTextField
import com.example.notes.ui.components.preview.PreviewColumn
import com.example.notes.ui.theme.color300
import com.example.notes.ui.theme.color400

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteContent(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
) {
    val textFieldColors = TextFieldDefaults.textFieldColors(
        textColor = color400,
        cursorColor = color300,
        containerColor = Color.White,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
    )

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier,
    ){
        NoteTextField(
            textFieldState = textFieldState,
            placeHolderText = "",
            colors = textFieldColors,
            singleLine = false,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun NewNoteContentPreview() {
    val keyboard = LocalSoftwareKeyboardController.current
    val textFieldState by remember { mutableStateOf(TextFieldState(keyboard)) }

    PreviewColumn(
        modifier = Modifier
            .background(color = Color.DarkGray)
    ) {
        NewNoteContent(
            modifier = Modifier.padding(16.dp),
            textFieldState = textFieldState,
        )
    }
}