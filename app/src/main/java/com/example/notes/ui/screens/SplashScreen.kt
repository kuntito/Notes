package com.example.notes.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notes.R
import com.example.notes.models.AppScreens
import com.example.notes.models.NoteEvent
import com.example.notes.ui.components.general.icons.AppLogo
import com.example.notes.ui.theme.color400
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onEvent: (NoteEvent) -> Unit,
) {
    Box(
        modifier = modifier
            .background(color = color400)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        LaunchedEffect(key1 = true) {
            onEvent(NoteEvent.OnSplash)
        }
        AppLogo()
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen{}
}