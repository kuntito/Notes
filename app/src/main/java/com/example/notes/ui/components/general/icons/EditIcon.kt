package com.example.notes.ui.components.general.icons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.R
import com.example.notes.ui.components.preview.PreviewColumn
import com.example.notes.ui.theme.color400

@Composable
fun EditIcon(
    modifier: Modifier = Modifier,
    size: Int = 24,
    tint: Color = Color.White,
    rippleColor: Color = Color.White,
    onClick: () -> Unit,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_edit),
        contentDescription = "edit icon",
        tint = tint,
        modifier = modifier
            .size(size.dp)
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
fun EditIconPreview() {
    PreviewColumn(
        modifier = Modifier
            .background(color = Color.DarkGray)
    ) {
        EditIcon(
            modifier = Modifier
                .size(48.dp)
        ) {}
    }
}