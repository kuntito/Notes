package com.example.notes.ui.components.general.text_field

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.notes.models.TextFieldState
import com.example.notes.ui.components.preview.PreviewColumn
import com.example.notes.ui.theme.color300
import com.example.notes.ui.theme.color400
import com.example.notes.ui.theme.ts300



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteTextField(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
    placeHolderText: String,
    placeHolderTextColor: Color = color300,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
    fontSize: TextUnit = ts300.fontSize,
    singleLine: Boolean = true,
) {
    val textValue by textFieldState.textValue.collectAsState()

    TextField(
        value = textValue,
        onValueChange = textFieldState::onSearchTextChange,
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(textFieldState.focusRequester)
            .onFocusChanged {
                textFieldState.onFocusChange(it.isFocused)
            }
            .height(56.dp),
        textStyle = LocalTextStyle.current
            .copy(fontSize = fontSize),
        placeholder = {
            PlaceHolderText(
                color = placeHolderTextColor,
                text = placeHolderText
            )
        },
        colors = colors,
        singleLine = singleLine,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Preview
@Composable
fun NoteTextFieldPreview() {
    PreviewColumn {
        val keyboard = LocalSoftwareKeyboardController.current
        val textFieldState by remember{ mutableStateOf(TextFieldState(keyboard)) }
        val placeHolderTextColor = color300
        val colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = placeHolderTextColor,
            containerColor = color400,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )

        NoteTextField(
            textFieldState = textFieldState,
            placeHolderTextColor = placeHolderTextColor,
            colors = colors,
            placeHolderText = "Untitled",
        )

        DisposableEffect(Unit){
            textFieldState.focusRequester.requestFocus()
            onDispose {  }
        }
    }
}