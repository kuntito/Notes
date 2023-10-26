package com.example.notes.ui.components.general

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.models.TextFieldState
import com.example.notes.ui.components.general.icons.SearchIcon

//TODO delete?
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteSearchView(
    modifier: Modifier = Modifier
) {
    var toggle by remember{ mutableStateOf(true) }
    val doToggle = { toggle = !toggle }
    val keyboard = LocalSoftwareKeyboardController.current
    val state = TextFieldState(keyboard)
    val size = 56
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = modifier
            .fillMaxWidth()
            .height(size.dp)
    ) {
        AnimatedVisibility(visible = toggle) {
            SearchIcon(
                modifier = Modifier
                    .size(size.dp)
                    .clickable {
                        doToggle()
                    }
            )
        }
        AnimatedVisibility(visible = !toggle) {
            SearchTextField(textFieldState = state)
        }
    }
}

@Preview
@Composable
fun SearchViewPreview() {
//    PreviewColumn(
//        modifier = Modifier.background(color = Color.Green)
//    ) {
//        NoteSearchView()
//    }
}