package com.example.notes.ui.components.general.icons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.ui.components.preview.PreviewColumn
import com.example.notes.ui.theme.color400


@Composable
fun CloseIcon(
    modifier: Modifier = Modifier,
    tint: Color = color400,
    rippleColor: Color = Color.White,
    onClick: () -> Unit,
) {
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Close Icon",
        tint = tint,
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = rippleColor),
            ) {
                onClick()
            },
    )
}

@Preview
@Composable
fun CloseIconPreview() {
    PreviewColumn(
        modifier = Modifier
    ) {
        CloseIcon(
            modifier = Modifier
                .size(48.dp)
        ) {}
    }
}