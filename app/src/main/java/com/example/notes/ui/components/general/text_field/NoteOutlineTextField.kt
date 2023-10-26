package com.example.notes.ui.components.general.text_field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.models.TextFieldState
import com.example.notes.ui.components.preview.PreviewColumn
import com.example.notes.ui.theme.color300
import com.example.notes.ui.theme.color400
import com.example.notes.ui.theme.ts300

// TODO delete?
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteOutlineTextField(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
    placeHolderText: String,
    placeHolderTextColor: Color,
    colors: TextFieldColors,
    isFocused: Boolean = false,
    shape: Shape = RectangleShape
) {
    val focusRequester = remember { FocusRequester() }
    val textValue by textFieldState.textValue.collectAsState()

    OutlinedTextField(
        value = textValue,
        onValueChange = textFieldState::onSearchTextChange,
        shape = shape,
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .height(56.dp),
        textStyle = LocalTextStyle.current
            .copy(fontSize = ts300.fontSize),
        placeholder = {
            PlaceHolderText(
                color = placeHolderTextColor,
                text = placeHolderText
            )
        },
        colors = colors
    )

    if (isFocused) {
        DisposableEffect(Unit) {
            focusRequester.requestFocus()
            onDispose { }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NoteOutlineTextField() {
    PreviewColumn {
        val textFieldState by remember{ mutableStateOf(TextFieldState(null)) }
        val placeHolderTextColor = color300
        val colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            cursorColor = placeHolderTextColor,
            containerColor = color400,
            focusedBorderColor = color300,
            unfocusedBorderColor = color400,
        )
/*        NoteOutlineTextField(
            textFieldState = textFieldState,
            placeHolderTextColor = placeHolderTextColor,
            colors = colors,
            placeHolderText = "Untitled",
        )*/
    }
}