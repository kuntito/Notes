package com.example.notes.ui.components.general

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notes.ui.components.getShadowModifier
import com.example.notes.ui.theme.color400

@Composable
fun NoteTopBar(
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        modifier
            .fillMaxWidth()
            .height(80.dp)
            .then(getShadowModifier()),
        color = color400,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            content = content,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp),
        )
    }
}