package com.example.notes.ui.components.general

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.models.TextFieldState
import com.example.notes.ui.components.general.icons.BackButton
import com.example.notes.ui.components.general.icons.CloseIcon
import com.example.notes.ui.components.general.icons.SearchIcon
import com.example.notes.ui.components.preview.PreviewColumn
import com.example.notes.ui.theme.color300
import com.example.notes.ui.theme.color400
import com.example.notes.ui.theme.ts300

@Composable
fun PlaceHolderText(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Text(
        text = "search",
        style = ts300.copy(
            color = color
        ),
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
) {
    val shape = RoundedCornerShape(28.dp)

    val colors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = color400,
        cursorColor = color300,
        containerColor = Color.White,
        focusedBorderColor = color300,
        unfocusedBorderColor = Color.Transparent
    )

    val textValue by textFieldState.textValue.collectAsState()

    OutlinedTextField(
        value = textValue,
        onValueChange = textFieldState::onSearchTextChange,
        shape = shape,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        textStyle = LocalTextStyle.current
            .copy(fontSize = ts300.fontSize),
        placeholder = {
            // FIXME for some reason, the `placeHolderColor` attribute of `TextFieldDefaults.outlinedTextFieldColors`
            //  doesn't change the placeholder color. Hence, it's hardcoded
            PlaceHolderText(color = color300)
        },
        leadingIcon = {
            SearchIcon()
        },
        trailingIcon = {
            AnimatedVisibility(textValue.text.isNotEmpty()) {
                CloseIcon(onClick = textFieldState::clearText)
            }
        },
        colors = colors,
        singleLine = true,
    )
}

@Composable
fun DismissableSearchTextField(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
    iconTint: Color = Color.White,
    rippleColor: Color = Color.Black,
    onDismiss: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        BackButton(
            onClick = onDismiss,
            tint = iconTint,
            rippleColor = rippleColor,
            modifier = Modifier
                .size(32.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        SearchTextField(
            textFieldState = textFieldState,
            modifier = Modifier
                .weight(1f)
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun NoteTextFieldPreview() {
    PreviewColumn(
        modifier = Modifier
            .background(color = color400)
            .padding(16.dp)
    ) {
        val keyboard = LocalSoftwareKeyboardController.current
        val textFieldState = TextFieldState(keyboard = keyboard)
        SearchTextField(
            textFieldState = textFieldState
        )
        DismissableSearchTextField(textFieldState = textFieldState) {

        }
    }
}