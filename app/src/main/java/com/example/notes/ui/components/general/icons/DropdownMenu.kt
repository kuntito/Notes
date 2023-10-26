package com.example.notes.ui.components.general.icons

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.notes.ui.components.preview.PreviewColumn
import com.example.notes.ui.theme.color400

enum class DropdownOption {
    Delete
}


@Composable
fun DropdownMenu(
    modifier: Modifier = Modifier,
    options: List<DropdownOption>,
    onOptionClick: (DropdownOption) -> Unit,
    tint: Color = color400,
    rippleColor: Color = Color.White,
    iconSize: Int = 24,
) {
    var isMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }
    val hideMenu: () -> Unit = {
        isMenuVisible = false
    }
    val showMenu: () -> Unit = {
        isMenuVisible = true
    }


    Box(modifier = Modifier) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "overflow menu",
            tint = tint,
            modifier = modifier
                .size(iconSize.dp)
                .clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = rememberRipple(color = rippleColor),
                ) {
                    showMenu()
                },
        )
        DropdownMenu(
            expanded = isMenuVisible,
            onDismissRequest = hideMenu) {
            options.forEach {opt ->
                DropdownMenuItem(
                    text = {
                        Text(text = opt.name)
                    },
                    onClick = {
                        onOptionClick(opt)
                        hideMenu()
                    })
            }
        }
    }
}

@Preview
@Composable
fun DropdownMenuPreview() {
    val options = listOf(
        DropdownOption.Delete
    )
    PreviewColumn(
        modifier = Modifier
    ) {
        DropdownMenu(
            options = options,
            onOptionClick = {},
            iconSize = 48
        )
    }
}