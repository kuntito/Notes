package com.example.notes.ui.components.general

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.R
import com.example.notes.ui.components.getShadowModifier
import com.example.notes.ui.components.preview.PreviewColumn
import com.example.notes.ui.theme.color200
import com.example.notes.ui.theme.color300
import com.example.notes.ui.theme.color400

private const val FAB_SIZE = 56
@Composable
private fun NoteFab(
    modifier: Modifier = Modifier,
    fabSize: Int = FAB_SIZE,
    bgColor: Color = color400,
    rippleColor: Color = color200,
    onClick: () -> Unit,
    @DrawableRes
    icon: Int,
    contentDescription: String,
) {
    Surface(
        shape = CircleShape,
        color = bgColor,
        modifier = modifier
            .size(fabSize.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = rippleColor),
            ) {
                onClick()
            }
            .then(getShadowModifier(shape = CircleShape))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = contentDescription,
                tint = Color.White,
                modifier = Modifier
                    .size((fabSize*0.5).dp)
            )
        }
    }
}

@Composable
fun FabAdd(
    modifier: Modifier = Modifier,
    fabSize: Int = FAB_SIZE,
    onAdd: () -> Unit
) {
    NoteFab(
        fabSize = fabSize,
        onClick = onAdd,
        icon = R.drawable.ic_add,
        contentDescription = "create note floating action button",
        modifier = modifier
    )
}

@Composable
fun FabSave(
    modifier: Modifier = Modifier,
    fabSize: Int = FAB_SIZE,
    onSave: () -> Unit,
) {
    NoteFab(
        fabSize = fabSize,
        onClick = onSave,
        icon = R.drawable.ic_save,
        contentDescription = "save note floating action button",
        modifier = modifier
    )
}

@Composable
fun FabDelete(
    modifier: Modifier = Modifier,
    fabSize: Int = FAB_SIZE,
    onDelete: () -> Unit,
) {
    NoteFab(
        fabSize = fabSize,
        onClick = onDelete,
        icon = R.drawable.ic_delete,
        contentDescription = "delete note floating action button",
        bgColor = Color.Red,
        rippleColor = Color.Black,
        modifier = modifier,
    )
}



@Preview
@Composable
fun NoteFabPreview() {
    PreviewColumn {
        FabAdd(fabSize = FAB_SIZE){}
        FabSave(fabSize = FAB_SIZE) {}
        FabDelete(fabSize = FAB_SIZE) {}
    }
}