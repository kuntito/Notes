package com.example.notes.models

import android.util.Log
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val textFieldTag = "textFieldTag"
class TextFieldState @OptIn(ExperimentalComposeUiApi::class) constructor(
    private val keyboard: SoftwareKeyboardController?,
    initText: String = ""
) {
    private val _textValue = MutableStateFlow(
        TextFieldValue(
            text = initText,
            selection = TextRange(initText.length),
        )
    )
    val textValue = _textValue.asStateFlow()
    val focusRequester = FocusRequester()

    private val _isFocused = MutableStateFlow(false)
    val isFocused = _isFocused.asStateFlow()

    @OptIn(ExperimentalComposeUiApi::class)
    fun onFocusChange(flag: Boolean) {
        _isFocused.value = flag
        if (flag) {
            // remove trailing white spaces
            _textValue.value = _textValue.value.also {
                it.copy(
                    text = it.text.trim()
                )
            }
            keyboard?.show()
        }
    }

    fun placeCursorAtTextEnd() {
        _textValue.value = _textValue.value.also {
            it.copy(selection = TextRange(it.text.length))
        }
    }

    fun onSearchTextChange(textFieldValue: TextFieldValue) {
        _textValue.value = textFieldValue
    }

    fun clearText() {
        _textValue.value = TextFieldValue("")
    }
}
