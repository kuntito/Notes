package com.example.notes.ui.components.general.icons

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.notes.R

@Composable
fun AppLogo(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_splash_logo),
        contentDescription = "app_logo",
        modifier = modifier
    )
}