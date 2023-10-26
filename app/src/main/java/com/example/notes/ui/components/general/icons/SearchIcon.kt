package com.example.notes.ui.components.general.icons

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.ui.components.preview.PreviewColumn
import com.example.notes.ui.theme.color400

@Composable
fun SearchIcon(
    modifier: Modifier = Modifier,
    tint: Color = color400
) {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = "Search Icon",
        tint = tint,
        modifier = modifier,
    )
}

@Preview
@Composable
fun SearchIconPreview() {
    PreviewColumn(
        modifier = Modifier
    ) {
        SearchIcon(
            modifier = Modifier
                .size(48.dp)
        )
    }
}