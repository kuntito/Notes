package com.example.notes.ui.screens.new_note.screen_components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.notes.models.NoteEvent
import com.example.notes.models.TextFieldState
import com.example.notes.ui.components.general.NoteTopBar
import com.example.notes.ui.components.general.icons.BackButton
import com.example.notes.ui.components.general.icons.CloseIcon
import com.example.notes.ui.components.general.icons.EditIcon
import com.example.notes.ui.components.general.text_field.NoteTextField
import com.example.notes.ui.components.preview.PreviewColumn
import com.example.notes.ui.theme.color300
import com.example.notes.ui.theme.color400
import com.example.notes.ui.theme.ts350
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

private class NewNoteTitleState {
    private val _isTitleEditable = MutableStateFlow(true)
    val isTitleEditable = _isTitleEditable.asStateFlow()

    fun disableTitleEdit() {
        _isTitleEditable.value = false
    }

    fun enableTitleEdit() {
        _isTitleEditable.value = true
    }
}

private const val newNoteTopBarTag = "newNoteTopBarTag"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteTitleTopBar(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
    onEvent: (NoteEvent) -> Unit,
) {
    val noteTitle by textFieldState.textValue.collectAsState()

    val placeHolderTextColor = color300
    val colors = TextFieldDefaults.textFieldColors(
        textColor = Color.White,
        cursorColor = placeHolderTextColor,
        containerColor = color400,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
    )

    val titleState by remember { mutableStateOf(NewNoteTitleState()) }

    val isFocused by textFieldState.isFocused.collectAsState()
    val isTitleEditable by titleState.isTitleEditable.collectAsState()
    val iconSize = 28

    LaunchedEffect(isFocused) {
        if (!isFocused && noteTitle.text.isNotEmpty()) {
            titleState.disableTitleEdit()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        NoteTopBar(modifier = modifier) {
            BackButton(
                onClick = { onEvent(NoteEvent.OnBackPressed) },
                tint = Color.White,
                rippleColor = Color.White,
                size = iconSize,
            )
            if (isTitleEditable) {
                LaunchedEffect(Unit) {
                    textFieldState.focusRequester.requestFocus()
                    textFieldState.placeCursorAtTextEnd()
                }
                NoteTextField(
                    textFieldState = textFieldState,
                    placeHolderText = "Untitled",
                    placeHolderTextColor = placeHolderTextColor,
                    colors = colors,
                    modifier = Modifier.weight(1f),
                )
                CloseIcon(
                    tint = placeHolderTextColor
                ) {
                    textFieldState.clearText()
                }
            } else {
                Text(
                    text = noteTitle.text,
                    style = ts350,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                EditIcon(size = iconSize) {
                    titleState.enableTitleEdit()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Preview
@Composable
fun NoteTopBarPreview() {
    val keyboard = LocalSoftwareKeyboardController.current
    val textFieldState by remember { mutableStateOf(TextFieldState(keyboard)) }
    PreviewColumn {
        NoteTitleTopBar(textFieldState = textFieldState){}
        TextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(text = "dummy text field")
            }
        )
    }
}
