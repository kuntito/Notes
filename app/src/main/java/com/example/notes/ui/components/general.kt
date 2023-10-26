package com.example.notes.ui.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp


fun getShadowModifier(shape: Shape? = null): Modifier {
    return if (shape == null) {
        Modifier.shadow(8.dp)
    } else {
        Modifier.shadow(8.dp, shape = shape)
    }
}

