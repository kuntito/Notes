package com.example.notes.ui.components.general

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.notes.R
import com.example.notes.models.NoteEvent
import com.example.notes.ui.components.general.icons.AppLogo
import com.example.notes.ui.components.preview.PreviewColumn
import com.example.notes.ui.theme.color400
import com.example.notes.ui.theme.ts300
import com.example.notes.ui.theme.ts350

@Composable
fun DeleteNoteDialog(
    modifier: Modifier = Modifier,
    onDeleteNote: () -> Unit,
    onDismiss: () -> Unit,
) {
    val textStyle = ts300.copy(fontWeight = FontWeight.SemiBold)
    AlertDialog(
        onDismissRequest = { onDismiss() },
        icon = {
           Icon(
               painter = painterResource(id = R.drawable.ic_note),
               contentDescription = "note icon",
               tint = Color.White,
               modifier = Modifier.size(24.dp)
           )
        },
        title = {
            Text(text = "Delete note?", style = textStyle)
        },
        confirmButton = {
            TextButton(onClick = onDeleteNote) {
                Text(text = "Yes", style = textStyle)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "No", style = textStyle)
            }
        },
        containerColor = color400,
        modifier = modifier,
    )
}
@Preview
@Composable
fun DeleteNoteDialogPreview() {
    PreviewColumn {
        DeleteNoteDialog(
            onDismiss = {},
            onDeleteNote = {},
        )
    }
}