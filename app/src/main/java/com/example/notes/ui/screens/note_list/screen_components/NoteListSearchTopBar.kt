package com.example.notes.ui.screens.note_list.screen_components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.notes.ui.components.general.DismissableSearchTextField
import com.example.notes.ui.components.general.icons.SearchIcon
import com.example.notes.ui.components.general.NoteTopBar
import com.example.notes.ui.components.preview.PreviewColumn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoteListTopBarState {
    private val _isOpenSearch = MutableStateFlow(false)
    val isOpenSearch = _isOpenSearch.asStateFlow()

    fun openSearch() {
        _isOpenSearch.value = true
    }

    fun closeSearch() {
        _isOpenSearch.value = false
    }
}

@Composable
fun NoteListSearchTopBar(
    modifier: Modifier = Modifier,
    noteListTopBarState: NoteListTopBarState,
    textFieldState: TextFieldState,
) {
    val isOpenSearch by noteListTopBarState.isOpenSearch.collectAsState()

    NoteTopBar(modifier = modifier) {
        AnimatedVisibility(
            visible = !isOpenSearch,
            modifier = Modifier.weight(1f)
        ) {
            HeaderTitle(text = "Notes")
        }
        AnimatedVisibility(visible = !isOpenSearch) {
            SearchIcon(
                modifier = Modifier
                    .size(32.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(color = Color.White),
                    ) {
                        noteListTopBarState.openSearch()
                    },
                tint = Color.White
            )
        }
        AnimatedVisibility(visible = isOpenSearch) {
            DismissableSearchTextField(
                textFieldState = textFieldState,
                onDismiss = noteListTopBarState::closeSearch,
                rippleColor = Color.White
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun TopAppBarPreview() {
    PreviewColumn {
        val noteListTopBarState by remember{ mutableStateOf(NoteListTopBarState()) }
        val keyboard = LocalSoftwareKeyboardController.current
        val textFieldState by remember { mutableStateOf(TextFieldState(keyboard)) }
        NoteListSearchTopBar(
            noteListTopBarState = noteListTopBarState,
            textFieldState = textFieldState
        )
    }
}