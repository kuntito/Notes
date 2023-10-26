package com.example.notes.ui.components.general.icons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.ui.components.preview.PreviewColumn

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    tint: Color,
    rippleColor: Color,
    size: Int = 24,
) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "back arrow",
        tint = tint,
        modifier = modifier
            .size(size.dp)
            .clickable(
                interactionSource = remember{MutableInteractionSource()},
                indication = rememberRipple(color = rippleColor)
            ) {
                onClick()
            },
    )
}

@Preview
@Composable
fun BackButtonPreview() {
    PreviewColumn {
        BackButton(
            modifier = Modifier
                .size(64.dp),
            onClick = { },
            tint = Color.Black,
            rippleColor = Color.Red
        )
    }
}